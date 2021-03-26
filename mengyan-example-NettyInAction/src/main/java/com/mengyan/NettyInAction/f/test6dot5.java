package com.mengyan.NettyInAction.f;

import com.mengyan.NettyInAction.ChannelSimulation;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

/**
 * 6-5 修改ChannelPipeline
 */
public class test6dot5 {

    public static void main(String[] args) {
        ChannelPipeline channelPipeline = ChannelSimulation.create().pipeline();
        // 创建一个FirstHandler实例
        TestChannelHandler firstHandler = new TestChannelHandler();
        // 将该实例作为'handler1'添加进Pipeline
        channelPipeline.addLast("handler1", firstHandler);
        // 将一个ChannelHandler实例作为'handler2'添加到Pipeline的第一个槽中，这意味着它将被放置在已有的'handler1'之前
        channelPipeline.addFirst("handler2", new TestChannelHandler());
        // 添加一个ChannelHandler的实例作为'handler3'添加到Pipeline的最后一个槽位中
        channelPipeline.addLast("handler3", new TestChannelHandler());

        // 通过名称将'handler3'移除
        channelPipeline.remove("handler3");
        // 通过引用移除firstHandler;
        channelPipeline.remove(firstHandler);
        // 将'handler2'替换为一个新的ChannelHandler实例'handler4'
        channelPipeline.replace("handler2","handler4", new TestChannelHandler());

    }

    /**
     * 用于模拟的ChannelHandler
     */
    public static class TestChannelHandler implements ChannelHandler {

        @Override
        public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

        }

        @Override
        public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

        }
    }
}
