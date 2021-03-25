package com.mengyan.NettyInAction.f;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * 6-1 释放消息资源
 * 6-2 使用SimpleChannelInboundHandler
 */
public class ReleaseHandlerMsg {

    /**
     * 6-1 释放消息资源
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
}
