package com.mengyan.NettyInAction.e;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;

/**
 * 5-15 引用计数
 */
public class test5dot15 {
    public static void main(String[] args) {
        Channel channel = ChannelSimulation.create();
        // 从channel获取ByteBufAllocator
        ByteBufAllocator allocator = channel.alloc();
        // 从ByteBufAllocator分配一个ByteBuf
        ByteBuf byteBuf = allocator.directBuffer();
        //检查引用计数器是否为预期1
        System.out.println(byteBuf.refCnt() == 1);
    }
}
