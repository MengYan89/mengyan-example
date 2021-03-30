package com.mengyan.NettyInAction.k;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * 11-5 使用Https
 * 启用Https只需要将SslHandler添加到ChannelPipeline的ChannelHandler组合中
 */
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean isClient;

    public HttpsCodecInitializer(SslContext context, boolean isClient) {
        this.context = context;
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        SSLEngine engine = context.newEngine(channel.alloc());
        // 将SSLHandler添加到ChannelPipeline中以使用Https
        pipeline.addFirst("ssl", new SslHandler(engine));
        if (isClient) {
            // 如果是客户端则添加HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            // 如果是服务端则添加HttpServerCodec
            pipeline.addLast("codec", new HttpServerCodec());
        }
    }
}
