package com.mengyan.NettyInAction.e;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 5-14 获取一个到ByteBufAllocator的引用
 */
public class test5dot14 {
    Channel channel = ChannelSimulation.create();
    // 从Channel获取一个ByteBufAllocator的引用
    ByteBufAllocator allocator = channel.alloc();

    ChannelHandlerContext ctx = null;
    ByteBufAllocator allocator2 = ctx.alloc();
}
