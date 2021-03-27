package com.mengyan.NettyInAction.h;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 8-4 引导客户端
 */
public class test8dot4 {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 创建SeverBootstrap
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置EventLoopGroup,其提供了用于处理Channel事件的EventLoop
        bootstrap.group(group)
                // 使用指定的Channel实现
                .channel(NioServerSocketChannel.class)
                // 设置用于处理已被接受的子Channel的IO及数据的ChannelInboundHandler
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                    }
                });
        // 通过配置好的ServerBootstrap的实例绑定该Channel 绑定服务器监听的端口
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    // 服务器已绑定
                    System.out.println("Server bound");
                } else {
                    System.err.println("Bound attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
