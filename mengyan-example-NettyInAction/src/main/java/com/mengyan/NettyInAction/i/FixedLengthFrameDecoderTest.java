package com.mengyan.NettyInAction.i;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * 9-2 测试FixedLengthFrameDecoder
 */
public class FixedLengthFrameDecoderTest {

    // 使用注解@Test标注,因此JUnit将会执行该方法
    @Test
    public void testFramesDecoded() {
        // 创建一个ByteBuf并储存9个字节
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        // 返回一个buf的浅拷贝
        ByteBuf input = buf.duplicate();
        // 创建一个EmbeddedChannel,并添加一个FixedLengthFrameDecoder,其将以3字节的帧长度进行测试
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // write bytes 将数据写入EmbeddedChannel
        Assert.assertTrue(channel.writeInbound(input.retain()));
        // 标记Channel为已完成状态
        Assert.assertTrue(channel.finish());

        // read message 读取所生成的消息,并且验证是否有3帧,其中每帧都为3字节
        ByteBuf read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        Assert.assertNull(channel.readInbound());
        buf.release();

    }

    /**
     * 第二个测试方法testFramesDecoded2
     */
    @Test
    public void testFramesDecoded2() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        // 返回一个buf的浅拷贝
        ByteBuf input = buf.duplicate();
        // 创建一个EmbeddedChannel,并添加一个FixedLengthFrameDecoder,其将以3字节的帧长度进行测试
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // 返回false 因为没有一个完整的可提供读取的帧
        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));
        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));

        Assert.assertTrue(channel.finish());

        // read message 读取所生成的消息,并且验证是否有3帧,其中每帧都为3字节
        ByteBuf read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        Assert.assertNull(channel.readInbound());
        buf.release();
    }
}
