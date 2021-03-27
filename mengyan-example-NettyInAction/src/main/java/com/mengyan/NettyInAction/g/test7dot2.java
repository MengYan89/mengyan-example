package com.mengyan.NettyInAction.g;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 7-2 使用ScheduleExecutorService调度任务
 */
public class test7dot2 {

    public static void main(String[] args) {
        // 创建一个其线程池具有10个线程的ScheduledExecutorService
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        ScheduledFuture<?> future = executor.schedule(
                // 创建一个Runnable以供调度稍后执行
                new Runnable() {
                    @Override
                    public void run() {
                        // 该任务要打印的消息
                        System.out.println("60 seconds later");
                    }
                    // 调度任务在从现在开始的60S后执行
                }, 60, TimeUnit.SECONDS);

        while (true) {
            if (future.getDelay(TimeUnit.SECONDS) <= 0) {
                break;
            }
        }
        // 一旦执行完就关闭ScheduledExecutorService释放资源
        executor.shutdown();
    }
}
