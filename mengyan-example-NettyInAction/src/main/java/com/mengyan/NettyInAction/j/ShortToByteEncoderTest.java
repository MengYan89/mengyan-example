package com.mengyan.NettyInAction.j;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import org.junit.Assert;
import org.junit.Test;

/**
 * 10-5 ShortToByteEncoderTest
 */
public class ShortToByteEncoderTest {

    // Short值都将会占用ByteBuf中的两个字节
    public static class ShortToByteEncoder extends MessageToByteEncoder<Short> {

        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext, Short aShort, ByteBuf byteBuf) throws Exception {
            // 将Short写入ByteBuf中
            byteBuf.writeShort(aShort);
        }
    }

    @Test
    public void testEncoder() {
        Short s = 2;
        EmbeddedChannel channel = new EmbeddedChannel(new ShortToByteEncoder());

        Assert.assertTrue(channel.writeOutbound(s));
        Assert.assertTrue(channel.finish());
        ByteBuf buf = channel.readOutbound();
        Assert.assertEquals(s, Short.valueOf(buf.readShort()));
        Assert.assertNull(channel.readOutbound());
    }
}
