package com.mengyan.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class HttpClient {
    public static void start(String host, int port) throws URISyntaxException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new HttpAggregatorInitializer(true));
            ChannelFuture future = bootstrap.connect(host, port).sync();
            URI uri = new URI("/setu/?apikey=873602305f3d30bd0f5451&r18=1");
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    uri.toASCIIString());
            request.headers().set(HttpHeaderNames.HOST, "api.lolicon.app");
            request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());

            future.channel().writeAndFlush(request.retain());
            future.channel().writeAndFlush(request);
            future.channel().closeFuture().sync().addListener(ChannelFutureListener.CLOSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws  URISyntaxException {
        start("api.lolicon.app", 80);
        /*
        InetAddress[] addresses = InetAddress.getAllByName("https://api.lolicon.app");
        if (addresses.length > 0) {
            String host = addresses[0].getHostAddress();
            start(host, 80);
        }
        */

    }
}
