package com.mengyan.NettyInAction.g;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;

/**
 * 7-3 使用EventLoop调度任务
 */
public class test7dot3 {

    public static void main(String[] args) {
        Channel channel = ChannelSimulation.create();
        ScheduledFuture<?> future = channel.eventLoop().schedule(
                // 创建一个Runnable以供调度稍后执行
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("60 seconds later");
                    }
                    // 调度任务从现在开始60s后运行
                }, 60, TimeUnit.SECONDS);
    }
}
