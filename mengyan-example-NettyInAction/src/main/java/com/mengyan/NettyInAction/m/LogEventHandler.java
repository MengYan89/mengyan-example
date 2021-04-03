package com.mengyan.NettyInAction.m;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 13-7 LogEventHandler
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
        // 创建StringBuilder,并且构建输出的字符串
        StringBuilder builder = new StringBuilder();
        builder.append(msg.getReceived())
                .append("[")
                .append(msg.getSource().toString())
                .append("] [")
                .append(msg.getLogfile())
                .append("] : ")
                .append(msg.getMsg());
        // 打印LogEvent的数据
        System.out.println(builder.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当异常发生时,打印栈跟踪信息,并关闭对应的Channel
        cause.printStackTrace();
        ctx.close();
    }
}
