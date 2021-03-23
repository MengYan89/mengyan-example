package com.mengyan.NettyInAction.two;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Echo客户端代码
 * IDEA执行时在program arguments中填写127.0.0.1 9999
 */
public class EchoClientDemo {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClientDemo.class.getSimpleName() + "<host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }

    /**
     * 2-3 客户端的ClientHandler
     *  需要通过扩展SimpleChannelInboundHandler来处理必要的任务
     *
     *
     *  ChannelInboundHandlerAdapter与SimpleChannelInboundHandler
     *  为什么客户端使用SimpleChannelInboundHandler不使用ChannelInboundHandlerAdapter
     *  SimpleChannelInboundHandler:
     *  当channelRead0方法完成时SimpleChannelInboundHandler会释放负责存储该消息ByteBuf的内存引用
     *  在EchoServerHandler中write()操作是异步的所以当channelRead0完成后仍有可能没有完成write()
     *
     *  ChannelInboundHandlerAdapter:
     *  消息会在channelReadComplete()中当writeAndFlush()方法调用时被释放
     *
     *
     */
    // 可被多个Channel共享
    @ChannelHandler.Sharable
    public static class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
        /**
         * 当从一个服务器收到一条消息时调用
         * @param channelHandlerContext
         * @param byteBuf
         * @throws Exception
         */
        public void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            // 输出获得的消息
            System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
        }

        /**
         * 在与服务器的连接已经建立后将被调用
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            // 连接成功建立后发送一条信息
            ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        }

        /**
         * 在处理过程中发生异常时调用
         * @param ctx
         * @param cause
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // 记录异常并关闭Channel
            cause.printStackTrace();
            ctx.close();
        }
    }

    /**
     * 2-4 EchoClient
     */
    public static class EchoClient {
        private final String host;
        private final int port;

        public EchoClient(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public void start() throws Exception {
            EventLoopGroup group = new NioEventLoopGroup();

            try {
                // 创建Bootstrap
                Bootstrap b = new Bootstrap();
                // 指定EventLoopGroup以处理客户端事件
                // 需要适用于NIO实现
                b.group(group)
                        // 适用于NIO传输的Channel类型
                        .channel(NioSocketChannel.class)
                        // 设置服务器的InetSocketAddress
                        .remoteAddress(new InetSocketAddress(host, port))
                        // 在创建Channel是想ChannelPipeline中添加一个EchoClientHandler
                        .handler(new ChannelInitializer<SocketChannel>() {
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new EchoClientHandler());
                            }
                        });

                // 连接到远程节点,阻塞等待直到连接完成
                ChannelFuture f = b.connect().sync();
                // 阻塞直到Channel关闭
                f.channel().closeFuture().sync();
            } finally {
                // 关闭线程池并释放所有资源
                group.shutdownGracefully().sync();
            }
        }

    }


}
