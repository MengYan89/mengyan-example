package com.mengyan.NettyInAction.e;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 5-16 释放引用计数的对象
 */
public class test5dot16 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.directBuffer();
        // 减少该对象的活动引用,当减少到0时,该对象被释放并且该方法返回true
        boolean released = byteBuf.release();
    }
}
