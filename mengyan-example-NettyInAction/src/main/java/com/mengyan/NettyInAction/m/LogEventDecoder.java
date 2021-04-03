package com.mengyan.NettyInAction.m;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 13-6 LogEventDecoder
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        // 获取对DatagramPacket中的数据(ByteBuf)的引用
        ByteBuf data = msg.content();
        // 获取该SEPARATOR的索引
        int idx = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);
        // 提取文件名
        String fileName = data.slice(0, idx).toString(CharsetUtil.UTF_8);
        // 提取日志消息
        String logMsg = data.slice(idx+1, data.readableBytes()).toString(CharsetUtil.UTF_8);
        // 构建一个新的LogEvent对象,并且将它添加到列表中
        LogEvent event = new LogEvent(msg.sender(),fileName, logMsg, System.currentTimeMillis());
        out.add(event);

    }
}
