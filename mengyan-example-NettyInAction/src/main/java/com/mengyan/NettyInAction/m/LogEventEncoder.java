package com.mengyan.NettyInAction.m;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * 13-2 LogEventEncoder
 */
public class LogEventEncoder extends MessageToMessageEncoder<LogEvent> {
    private final InetSocketAddress remoteAddress;

    /**
     * LogEventEncoder创建了即将被发送到指定的InetSocketAddress的DatagramPacket消息
     * @param remoteAddress
     */
    public LogEventEncoder(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, LogEvent msg, List<Object> out) throws Exception {
        byte[] file = msg.getLogfile().getBytes(CharsetUtil.UTF_8);
        byte[] message = msg.getMsg().getBytes(CharsetUtil.UTF_8);
        ByteBuf buf = ctx.alloc().buffer(file.length + message.length + 1);
        // 将文件名写入
        buf.writeBytes(file);
        // 添加一个SEPARATOR
        buf.writeByte(LogEvent.SEPARATOR);
        // 将日志消息写入ByteBuf中
        buf.writeBytes(message);
        // 将一个拥有数据和目的地地址的新DatagramPacket添加到出站消息列表中
        out.add(new DatagramPacket(buf, remoteAddress));
    }
}
