package com.mengyan.NettyInAction.l;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 12-2 处理文本帧
 * 用来处理TextWebSocketFrame帧的Handler,其余的类型可以使用Netty提供的WebSocketServerProtocolHandler来处理
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    // 重写userEventTriggered()方法以处理自定义事件
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            // 如果该事件表示握手成功,则从该ChannelPipeline中移除HttpRequestHandler,因为将不会收到任何Http消息了
            ctx.pipeline().remove(HttpRequestHandler.class);
            // 通知所有已连接的WebSocket客户端新的客户端已经连接了
            group.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined"));
            // 将新的WebSocket Channel添加到ChannelGroup中,以便它可以接受到所有的信息
            group.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 添加消息的引用计数器,并将它写到ChannelGroup中所有已经连接的客户端
        group.writeAndFlush(msg.retain());
    }
}
