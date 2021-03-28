package com.mengyan.NettyInAction.h;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 8-9 优雅关闭
 */
public class test8dot9 {
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
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
        // 释放所有资源,并且关闭所有的当前正在使用的Channel
        Future<?> future = group.shutdownGracefully();
        // block until the group has shutdown 阻塞直到group关闭
        while (true) {
            if (future.isDone()) {
                break;
            }
        }
    }
}
