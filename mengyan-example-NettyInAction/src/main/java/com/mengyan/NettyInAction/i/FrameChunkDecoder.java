package com.mengyan.NettyInAction.i;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * 9-5 FrameChunkDecoder
 * 如果一个帧的大小超出了限制，那么程序将会抛弃它的字节,并抛出一个TooLongFrameException
 * 扩展ByteToMessageDecoder以将入站字节解码为消息
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {
    private final int maxFrameSize;

    // 指定将要产生的帧的最大允许大小
    public FrameChunkDecoder(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readableBytes = byteBuf.readableBytes();
        // 如果该帧太大,则丢弃它并抛出一个TooLongFrameException
        if (readableBytes > maxFrameSize) {
            // discard the bytes
            byteBuf.clear();
            throw new TooLongFrameException();
        }
        // 否则,从ByteBuf中读取一个新帧
        ByteBuf buf = byteBuf.readBytes(readableBytes);
        // 将该帧添加到解码消息的List中
        list.add(buf);
    }
}
