package com.mengyan.NettyInAction.j;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 10-1 ToIntegerDecoder类扩展了ByteToMessageDecoder
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 检查是否至少有4个字节可读(一个int的字节长度)
        if (byteBuf.readableBytes() >= 4) {
            // 从入站ByteBuf中读取一个int,并将其添加到解码消息的List中
            list.add(byteBuf.readInt());
        }
    }
}
