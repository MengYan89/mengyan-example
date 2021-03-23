package com.mengyan.NettyInAction.d;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 4-3 使用Netty的阻塞网络处理
 * 简单的接受连接并向客户端写"Hi!",然后关闭连接
 */
public class test4dot3 {
    
    public static void main(String[] args) throws Exception {
        NettyOioServer server = new NettyOioServer();
        server.server(9999);
    }

    public static class NettyOioServer {

        public void server(int port) throws Exception {
            final ByteBuf buf = Unpooled.unreleasableBuffer(
                    Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8"))
            );
            // 创建一个OIO的EventLoopGroup
            EventLoopGroup group = new OioEventLoopGroup();

            try {
                // 创建ServerBootstrap
                ServerBootstrap b = new ServerBootstrap();
                // 使用OioEventLoopGroup以允许阻塞模式
                b.group(group)
                        .channel(OioServerSocketChannel.class)
                        .localAddress(new InetSocketAddress(port))
                        // 指定ChannelInitializer,对于每个已经接受的连接都调用它
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(
                                        // 添加一个ChannelInboundHandlerAdapter以拦截和处理事件
                                        new ChannelInboundHandlerAdapter() {
                                            @Override
                                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                // 将消息写至客户端,并添加ChannelFutureListener以便消息一被写完就关闭连接
                                                ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                            }
                                        }
                                );
                            }
                        });
                // 绑定服务器以接受连接
                ChannelFuture f = b.bind().sync();
                f.channel().closeFuture().sync();
            } finally {
                // 释放所有资源
                group.shutdownGracefully().sync();
            }
        }
    }
}
