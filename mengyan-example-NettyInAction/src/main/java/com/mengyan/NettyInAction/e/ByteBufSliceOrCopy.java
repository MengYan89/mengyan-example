package com.mengyan.NettyInAction.e;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * 5-10 对ByteBuf进行切片
 * 5-11 复制一个ByteBuf
 */
public class ByteBufSliceOrCopy {

    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        /************ 5-10 start ************/
        System.out.println("************ 5-10 start ************");
        // 创建一个用于保存给定字符串的字节的ByteBuf
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 创建该ByteBuf从索引0开始至索引15结束的一个新切片
        ByteBuf sliced = buf.slice(0, 15);
        // 将打印 Netty in Action
        System.out.println(sliced.toString(utf8));
        // 更新索引0处的字节
        buf.setByte(0, (byte)'J');
        // 将会输出true,因为数据是共享的,对其中一个所作的改变对另一个也是可见的
        // 原文使用assert判断boolean表达式,在这里为了程序的连贯性将其打印出来
        System.out.println(buf.getByte(0) == sliced.getByte(0));
        System.out.println("************ 5-10 end ************");
        /************ 5-10 end ************/

        /************ 5-11 start ************/
        System.out.println("************ 5-11 start ************");
        // 创建一个用于保存给定字符串的字节的ByteBuf
        ByteBuf bufCopy = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        // 创建该ByteBuf从索引0开始至索引15结束的分段的副本
        ByteBuf copy = bufCopy.copy(0,15);
        // 将打印 Netty in Action
        System.out.println(copy.toString(utf8));
        // 更新索引0处的字节
        buf.setByte(0, (byte)'J');
        // 将会输出true,因为数据是不共享的
        // 原文使用assert判断boolean表达式,在这里为了程序的连贯性将其打印出来
        System.out.println(bufCopy.getByte(0) != copy.getByte(0));
        System.out.println("************ 5-11 end ************");
        /************ 5-11 end ************/
    }
}
