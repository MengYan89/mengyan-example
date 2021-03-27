package com.mengyan.NettyInAction.h;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 8-1 引导一个客户端
 * 引导一个使用NIO TCP传输的客户端
 */
public class test8dot1 {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个Bootstrap类的实例一创建和连接新的客户端Channel
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoopGroup,提供用于处理Channel事件的EventLoop
        bootstrap.group(group)
                // 指定要使用的Channel实现
                .channel(NioSocketChannel.class)
                // 设置用于Channel事件和数据的ChannelInboundHandler
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                    }
                });
        // 连接到远程主机
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    // 已建立连接
                    System.out.println("Connection established");
                } else {
                    System.err.println("Connection attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
