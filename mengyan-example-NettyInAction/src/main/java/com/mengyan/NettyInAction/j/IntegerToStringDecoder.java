package com.mengyan.NettyInAction.j;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 10-3 IntegerToStringDecoder类
 * 将Integer解码为String
 */
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Integer integer, List<Object> list) throws Exception {
        //将Integer消息转换为它的String表示,将其添加到输出的List中
        list.add(String.valueOf(integer));
    }
}
