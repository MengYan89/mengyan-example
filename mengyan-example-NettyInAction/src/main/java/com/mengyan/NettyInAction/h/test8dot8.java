package com.mengyan.NettyInAction.h;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * 8-8 使用Bootstrap和DatagramChannel
 * Netty提供了各种DatagramChannel的实现。唯一区别就是，不再调用connect()方法,而是只调用bind()方法
 */
public class test8dot8 {
    public static void main(String[] args) {
        // 创建一个Bootstrap的实例以创建和绑定新的数据报Channel
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoopGroup，其提供了用以处理Channel时间的EventLoop
        bootstrap.group(new OioEventLoopGroup())
                // 指定Channel的实现
                .channel(OioDatagramChannel.class)
                // 设置用以处理Channel的IO以及数据的ChannelInboundHandler
                .handler(new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
                        // Do something with the packet
                    }
                });
        // 调用bind()方法,因为该协议是无连接的
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(0));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Channel bound");
                } else {
                    System.err.println("Bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
