package com.mengyan.NettyInAction.j;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 10-6 IntegerToStringEncoder
 * 专业用法可参照ProtobufEncoder
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, List<Object> list) throws Exception {
        // 将Integer转换为String并将其添加到List中
        list.add(String.valueOf(integer));
    }
}
