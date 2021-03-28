package com.mengyan.NettyInAction.i;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 9-6 测试FrameChunkDecoder
 */
public class FrameChunkDecoderTest {

    @Test
    public void testFramesDecoded() {
        // 创建一个ByteBuf,并写入9字符
        ByteBuf byteBuf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            byteBuf.writeByte(i);
        }
        ByteBuf input = byteBuf.duplicate();

        // 创建一个EmbeddedChannel,并想起安装一个帧大小为3字节的FrameChunkDecoder
        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));
        // 向他写入2字节,并断言它们将会生产一个新帧
        assertTrue(channel.writeInbound(input.readBytes(2)));
        try {
            // 写入一个4字节大小的帧,并捕获预期的TooLongFrameException
            channel.writeInbound(input.readBytes(4));
            // 如果上面没有抛出异常，那么就会到达这个断言,并且测试失败
            Assert.fail();
        } catch (TooLongFrameException e) {
            // expected exception
            // 如果ChannelHandler实现了exceptionCaught方法并处理了该异常,那么他不会被catch块所捕捉
        }

        // 写入剩余的3字节并断言将生成一个有效帧
        assertTrue(channel.writeInbound(input.readBytes(3)));
        // 将该Channel标记为已完成状态
        assertTrue(channel.finish());

        // Read frames
        //读取产生的消息,并验证值
        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(byteBuf.readSlice(2), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        assertEquals(byteBuf.skipBytes(4).readSlice(3), read);
        read.release();
        byteBuf.release();
    }
}
