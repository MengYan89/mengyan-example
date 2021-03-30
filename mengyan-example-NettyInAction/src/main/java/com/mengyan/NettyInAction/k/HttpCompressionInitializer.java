package com.mengyan.NettyInAction.k;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 11-4 自动压缩HTTP信息
 */
public class HttpCompressionInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpCompressionInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 如果是客户端,则添加HttpClientCodec
        if (isClient) {
            pipeline.addLast("codec", new HttpClientCodec());
            // 添加HttpContentDecompressor以处理来自服务器的压缩内容
            pipeline.addLast("decompressor", new HttpContentDecompressor());
        } else {
            // 如果是服务器,则添加HttpServerCodec
            pipeline.addLast("codec", new HttpServerCodec());
            // 如果是服务器,则添加httpContentCompressor来压缩数据
            pipeline.addLast("compressor", new HttpContentCompressor());
        }
    }
}
