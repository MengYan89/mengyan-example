package com.mengyan.NettyInAction.e;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * 5-13 Byte上的read()和write()操作
 */
public class ByteReadAndWrite {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        // 创建一个ByteBuf以保存给定的字符串字节
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 打印第一个字符'N'
        System.out.println((char)buf.readByte());
        // 保存当前索引
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        // 将字符'?'追加至缓冲区
        buf.writeByte((char)'?');
        // 都会输出true 因为writeByte方法移动了writerIndex
        System.out.println(readerIndex == buf.readerIndex());
        System.out.println(writerIndex != buf.writerIndex());

    }
}
