package com.mengyan.NettyInAction.one;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 1-2 被回调触发的ChannelHandler
 */
public class test1dot2 {

    public class ConnectHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            // 当一个新的连接被建立时channelActive将会被调用
            System.out.println("Client: " + ctx.channel().remoteAddress() + " connected");
        }
    }
}
