package com.mengyan.NettyInAction.j;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 10-9 CharToByteEncoder类
 */
public class CharToByteEncoder extends MessageToByteEncoder<Character> {
    // 将Character解码为char,并将其写入到出站ByteBuf中
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Character character, ByteBuf byteBuf) throws Exception {
        byteBuf.writeChar(character);
    }
}
