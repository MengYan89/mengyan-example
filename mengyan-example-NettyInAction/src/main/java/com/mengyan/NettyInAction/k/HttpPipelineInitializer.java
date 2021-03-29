package com.mengyan.NettyInAction.k;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 11-2 添加HTTP支持
 */
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {
    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        if (client) {
            // 如果是客户端,则添加httpResponseDecoder以处理来自服务器的响应
            pipeline.addLast("decoder", new HttpResponseDecoder());
            // 如果是客户端,则添加HttpRequestEncoder以向服务端发送请求
            pipeline.addLast("encoder", new HttpRequestEncoder());
        } else {
            // 如果是服务器则添加HttpRequestDecoder以处理来自客户端的请求
            pipeline.addLast("decoder", new HttpRequestDecoder());
            // 如果是服务器则添加HttpResponseEncoder以向客户端发送响应
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }
    }
}
