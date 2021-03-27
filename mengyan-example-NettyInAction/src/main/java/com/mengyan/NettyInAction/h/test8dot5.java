package com.mengyan.NettyInAction.h;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 8-5 引导服务器
 * 从子Channel中引导客户端并让新的客户端共享Channel的EventLoop
 */
public class test8dot5 {

    public static void main(String[] args) {
        // 创建ServerBootstrap以创建ServerSocketChannel, 并绑定它
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置EventLoopGroup,其将提供用以处理Channel事件的EventLoop
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                // 指定要使用的Channel实现
                .channel(NioServerSocketChannel.class)
                // 设定用于处理已被接受的子Channel的IO和数据的ChannelInboundHandler
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    ChannelFuture connectFuture;
                    // 当一个新的连接被建立时channelActive将会被调用
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        // 创建一个Bootstrap类的实例以连接到远程主机
                        Bootstrap bootstrap = new Bootstrap();

                        // 指定Channel的实现
                        bootstrap.channel(NioSocketChannel.class).handler(
                                // 为入站IO设置ChannelInboundHandler
                                new SimpleChannelInboundHandler<ByteBuf>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                        System.out.println("Received data");
                                    }
                                }
                        );
                        // 使用与分配给已被接受的子Channel相同的EventLoop
                        bootstrap.group(ctx.channel().eventLoop());
                        // 连接到远程节点
                        connectFuture = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        if (connectFuture.isDone()) {
                            // 当连接完成时,执行的一些数据操作
                        }
                    }
                });
        // 通过配置好的ServerBootstrap绑定该ServerSocketChannel
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Server bound");
                } else {
                    System.err.println("Bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
