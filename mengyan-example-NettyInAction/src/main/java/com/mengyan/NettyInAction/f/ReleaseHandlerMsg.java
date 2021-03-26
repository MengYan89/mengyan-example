package com.mengyan.NettyInAction.f;

import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

/**
 * 6-1 释放消息资源
 * 6-2 使用SimpleChannelInboundHandler
 * 6-3 消费并释放入站消息
 * 6-4 丢弃并释放出栈消息
 */
public class ReleaseHandlerMsg {

    /**
     * 6-1 释放消息资源
     * 6-3 消费并释放入站消息
     * 当某个ChannelInboundHandler的实现重写channelRead方法时
     * 它负责显式的释放与池化ByteBuf实例相关的内存
     */
    @ChannelHandler.Sharable
    public class DiscardHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            // 丢弃已接收的消息
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 6-2 使用SimpleChannelInboundHandler
     * SimpleChannelInboundHandler会自动释放资源，所以应该注意不要储存指向任何消息的引用进行使用
     * 因为这些引用都会失效
     */
    public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object> {
        @Override
        public void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
            // 不需要任何显式资源释放
        }
    }

    /**
     * 6-4 丢弃并释放出栈消息
     * 不仅要释放资源，还要通知ChannelPromise
     * 如果有一个消息被消费或者被丢弃了,并且没有传递给ChannelPipeline中的下一个ChannelOutboundHandler
     * 用户就有责任调用ReferenceCountUtil.release
     */
    public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            // 通过使用ReferenceCountUtil.release释放资源
            ReferenceCountUtil.release(msg);
            // 通知ChannelPromise数据已经被处理
            promise.setSuccess();
        }
    }
}
