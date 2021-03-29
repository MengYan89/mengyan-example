package com.mengyan.NettyInAction.k;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * 11-1 添加SSL/TLS支持
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {
    private final SslContext context;
    private final boolean startTls;

    /**
     * 传入要使用的SslContext
     * 如果设置为true,第一个写入的消息将不会被加密(客户端应该设置为true)
     * @param context
     * @param startTls
     */
    public SslChannelInitializer(SslContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        // 对于每个SslHandler实例,都使用Channel的ByteBufAllocator从SslContext获取一个新的SSLEngine
        SSLEngine engine = context.newEngine(channel.alloc());
        //将SslHandler作为第一个ChannelHandler添加到ChannelPipeline中
        channel.pipeline().addFirst("ssl", new SslHandler(engine, startTls));
    }
}
