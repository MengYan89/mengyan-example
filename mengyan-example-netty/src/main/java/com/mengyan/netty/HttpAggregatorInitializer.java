package com.mengyan.netty;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLEngine;

/**
 * 自动聚合HTTP的消息片段
 */
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpAggregatorInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        if (isClient) {
            // 如果是客户端,则添加HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());
            // pipeline.addLast("aggregator", new HttpObjectAggregator(64 * 1024));
            pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
            pipeline.addLast("httpClient", new HttpClientHandler());
            pipeline.addLast("decompressor", new HttpContentDecompressor());
            // SelfSignedCertificate cert = new SelfSignedCertificate();
            // SslContext context = SslContext.newServerContext(cert.certificate(), cert.privateKey());
            // SSLEngine engine = context.newEngine(channel.alloc());
            // pipeline.addFirst("ssl", new SslHandler(engine, true));
        } else {
            // 如果是服务端,则添加HttpServerCodec
            pipeline.addLast("codec", new HttpServerCodec());
            pipeline.addLast("httpClient", new HttpClientHandler());
            pipeline.addLast("server",new SimpleChannelInboundHandler<HttpRequest>(){
                protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
                    System.out.println(httpRequest);
                }
            });
        }
        // 将最大的消息大小为512KB的HttpObjectAggregator添加到ChannelPipeline
        // pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
    }
}
