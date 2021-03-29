package com.mengyan.NettyInAction.j;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 10-8 ByteToCharDecoder类
 */
public class ByteToCharDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 将一个或者多个Character对象添加到传出的List中
        while (byteBuf.readableBytes() >= 2) {
            list.add(byteBuf.readChar());
        }
    }
}
