package com.mengyan.NettyInAction.e;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * 5-12 get()和set()方法的用法
 */
public class ByteGetAndSet {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        // 创建一个ByteBuf以保存给定的字节
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 打印第一个字符'N'
        System.out.println((char) buf.getByte(0));
        // 储存当前readerIndex和writerIndex
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        // 将索引0处的字节更新为字符B
        buf.setByte(0, (byte)'B');
        // 打印第一个字符现在是'B'
        System.out.println((char) buf.getByte(0));
        // 下面的打印都会是true 因为这些操作并不会修改相应的索引
        System.out.println(buf.readerIndex() == readerIndex);
        System.out.println(buf.writerIndex() == writerIndex);

    }
}
