package com.mengyan.NettyInAction.l;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * 12-6 为ChannelPipeLine 添加加密
 * 扩展ChatServerInitializer添加加密
 */
public class SecureChatServerInitializer extends ChatServerInitializer {
    private final SslContext context;

    public SecureChatServerInitializer(ChannelGroup group, SslContext context) {
        super(group);
        this.context = context;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        // 调用父类的initChannel
        super.initChannel(ch);
        SSLEngine engine = context.newEngine(ch.alloc());
        engine.setUseClientMode(false);
        // 将SslHandler添加到ChannelPipeline中
        ch.pipeline().addFirst(new SslHandler(engine));
    }
}
