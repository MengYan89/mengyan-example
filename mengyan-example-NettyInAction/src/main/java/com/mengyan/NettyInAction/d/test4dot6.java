package com.mengyan.NettyInAction.d;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 4-6 从多个线程使用同一个channel
 *
 * Netty的Channel是线程安全的,因此你可以储存一个Channel的引用,给多个线程使用
 */
public class test4dot6 {

    public static void main(String[] args) {
        final Channel channel = ChannelSimulation.create();
        // 创建持有要写数据的ByteBuf
        final ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8).retain();
        // 创建将数据写到Channel的Runnable
        Runnable writer = new Runnable() {
            @Override
            public void run() {
                channel.writeAndFlush(buf.duplicate());
            }
        };
        // 获取到线程池Executor的引用
        Executor executor = Executors.newCachedThreadPool();
        // 递交写任务给线程池
        executor.execute(writer);

        executor.execute(writer);
    }
}
