package com.mengyan.NettyInAction.j;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 10-2 ToIntegerDecoder2扩展了ReplayingDecoder
 * 如果没有足够的字节调用如readInt()等方法将会抛出一个Error,其将在基类中被捕获
 * 并不是所有的ByteBuf的操作都被支持，如果调用了一个不被支持的方法将会抛出一个UnsupportedOperationException
 * ReplayingDecoder稍慢于ByteToMessageDecoder
 */
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 传入的ByteBuf实际为ReplayingDecoderByteBuf
        // 从入站的ByteBuf中读取一个Int,并将其添加到解码消息List中
        list.add(byteBuf.readInt());
    }
}
