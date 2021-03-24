package com.mengyan.NettyInAction.d;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.CharsetUtil;

/**
 * 4-5 写出到Channel
 */
public class test4dot5 {

    public static void main(String[] args) {
        Channel channel = ChannelSimulation.create();
        // 创建持有要写数据的ByteBuf
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        // 写数据并冲刷他
        ChannelFuture cf = channel.writeAndFlush(buf);
        // 添加一个ChannelFutureListener以便在写操作完成后接受通知
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                // 写操作完成并没有报错
                if (channelFuture.isSuccess()) {
                    System.out.println("Write successful");
                } else {
                    // 记录错误
                    System.err.println("Write error");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
