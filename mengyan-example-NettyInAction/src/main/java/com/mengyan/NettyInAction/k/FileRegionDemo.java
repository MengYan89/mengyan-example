package com.mengyan.NettyInAction.k;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.channel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 11-11 使用FileRegion传输文件的内容
 * 如何通过从FileInputStream创建一个DefaultFileRegion,并将其写入Channel,从而利用零拷贝特性来传输一个文件的内容
 * 甚至可以利用ChannelProgressivePromise来实时获取传输的进度
 */
public class FileRegionDemo {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("test");
        // 创建一个FileInputStream
        FileInputStream in = new FileInputStream(file);
        // 以该文件的完整长度创建一个DefaultFileRegion
        FileRegion region = new DefaultFileRegion(in.getChannel(), 0, file.length());
        final Channel channel = ChannelSimulation.create();
        // 发送该DefaultFileRegion并注册一个ChannelFutureListener
        channel.writeAndFlush(region).addListener(
                new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (! channelFuture.isSuccess()) {
                            // 处理失败
                            Throwable cause = channelFuture.cause();
                        }
                    }
                }
        );
    }
}
