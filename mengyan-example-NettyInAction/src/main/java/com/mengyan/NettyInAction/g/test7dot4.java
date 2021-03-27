package com.mengyan.NettyInAction.g;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;

/**
 * 7-4 使用EventLoop调度周期性的任务
 */
public class test7dot4 {
    public static void main(String[] args) {
        Channel channel = ChannelSimulation.create();
        //创建一个Runnable以供调度稍后执行
        ScheduledFuture<?> future = channel.eventLoop().scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        // 这将一直运行，直到ScheduledFuture被取消
                        System.out.println("Run every 60 seconds");
                    }
                    // 调度在60s之后,并且以后每隔60s执行一次
                }, 60, 60, TimeUnit.SECONDS);
    }
}
