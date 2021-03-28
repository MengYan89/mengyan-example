package com.mengyan.NettyInAction.i;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 9-4 测试AbsIntegerEncoder
 */
public class AbsIntegerEncoderTest {

    @Test
    public void testEncoded() {
        // 创建一个ByteBuf并且写入9个整数
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        // 创建一个EmbeddedChannel并安装一个要测试的AbsIntegerEncoder
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());

        // 写入ByteBuf并断言调用readOutbound方法将会产生数据
        assertTrue(channel.writeOutbound(buf));
        // 将该Channel标记为已完成状态
        assertTrue(channel.finish());

        // read bytes
        // 读取所产生的消息并断言它们包含了对应的绝对值
        for (int i = 1; i < 10; i++) {
            assertEquals(i, channel.readOutbound());
        }
        assertNull(channel.readOutbound());
    }
}
