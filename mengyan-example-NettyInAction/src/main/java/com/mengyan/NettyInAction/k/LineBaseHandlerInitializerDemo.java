package com.mengyan.NettyInAction.k;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

public class LineBaseHandlerInitializerDemo {

    /**
     * 被LineBasedFrameDecoder处理过的帧行尾分隔符会"\r\n"被去除
     */
    @Test
    public void lineBaseHandlerInitializerTest() {
        ByteBuf in = Unpooled.buffer();
        in.writeBytes(Unpooled.copiedBuffer("ABC\r\nDEF\r\n", Charset.forName("UTF-8")));

        EmbeddedChannel channel = new EmbeddedChannel(new LineBaseHandlerInitializer());
        Assert.assertTrue(channel.writeInbound(in));

        Assert.assertTrue(channel.finish());
        ByteBuf read = channel.readInbound();
        Assert.assertEquals(read.toString(Charset.forName("UTF-8")) ,"ABC");
        read.release();

        read = channel.readInbound();
        // Assert.assertEquals(read.toString(Charset.forName("UTF-8")) ,"DEF\r\n");
        Assert.assertNotEquals(read.toString(Charset.forName("UTF-8")) ,"DEF\r\n");
        read.release();

        Assert.assertNull(channel.readInbound());


    }

    /**
     * 11-8 处理由行尾符分隔的帧
     */
    public static final class LineBaseHandlerInitializer extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel channel) throws Exception {
            ChannelPipeline pipeline = channel.pipeline();
            // 该LineBasedFrameDecoder将提取的帧转发给下一个ChannelInboundHandler
            pipeline.addLast(new LineBasedFrameDecoder(64* 1024));
            // 添加FrameHandler以接受帧
            // pipeline.addLast(new FrameHandler());
        }

    }
}
