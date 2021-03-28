package com.mengyan.NettyInAction.j;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * 10-4 TooLongFrameException
 */
public class SafeByteToMessageDecoder extends ByteToMessageDecoder {
    // 最大可以接受的字节数
    private static final int MAX_FRAME_SIZE = 1024;
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readable = byteBuf.readableBytes();
        // 检查缓冲区中是否有超过MAX_FRAME_SIZE个字节
        if (readable > MAX_FRAME_SIZE) {
            // 跳过所有可读字节抛出TooLongFrameException并通知ChannelHandler
            byteBuf.skipBytes(readable);
            throw new TooLongFrameException("Frame too big!");
        }
    }
}
