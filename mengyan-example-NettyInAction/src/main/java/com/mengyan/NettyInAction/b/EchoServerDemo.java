package com.mengyan.NettyInAction.b;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Echo服务端代码
 * IDEA执行时在program arguments中填写9999
 */
public class EchoServerDemo {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServerDemo.class.getSimpleName() + "<port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    /**
     * 2-1 EchoServerHandler
     */
    // 可以被多个Channel安全的共享
    @ChannelHandler.Sharable
    public static class EchoServerHandler extends ChannelInboundHandlerAdapter {

        /**
         * 每个消息传入都会调用
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf)msg;
            System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
            // 将接受的消息写给发送者，不冲刷出站消息(将消息加入出站缓冲区但不进行发送)
            ctx.write(in);
        }

        /**
         * 读取当前批量读取中的最后一条消息（当channelRead读取完最后一条消息后调用）
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            // 将消息发送至远程节点,并关闭Channel
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                    .addListener(ChannelFutureListener.CLOSE);
        }

        /**
         * 读取操作期间有异常抛出会调用
         * @param ctx
         * @param cause
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // 打印异常栈跟踪
            cause.printStackTrace();
            // 关闭该channel
            ctx.close();
        }
    }

    /**
     * 2-2 EchoServer
     */
    public static class EchoServer {
        private final int port;

        public EchoServer(int port) {
            this.port = port;
        }

        public void start() throws Exception {
            // 创建2-1编写的EchoServerHandler
            final EchoServerHandler serverHandler = new EchoServerHandler();
            // 创建EventLoopGroup
            EventLoopGroup group = new NioEventLoopGroup();

            try {
                // 创建ServerBootstrap
                ServerBootstrap b = new ServerBootstrap();

                b.group(group);
                b.channel(NioServerSocketChannel.class);
                b.localAddress(new InetSocketAddress(port));
                b.childHandler(new ChannelInitializer<SocketChannel>() {
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(serverHandler);
                    }
                });
                // 异步地绑定服务器
                // 调用sync()方法阻塞等待直到绑定完成
                ChannelFuture f = b.bind().sync();
                // 获取Channel的CloseFuture,并且阻塞当前线程直到它完成
                f.channel().closeFuture().sync();
            } finally {
                // 关闭EventLoopGroup,释放所有资源
                group.shutdownGracefully().sync();
            }


        }
    }
}
