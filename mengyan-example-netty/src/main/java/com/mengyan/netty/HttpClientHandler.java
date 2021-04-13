package com.mengyan.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import jdk.nashorn.internal.runtime.URIUtils;

import java.net.URI;
import java.util.concurrent.Future;

public class HttpClientHandler extends ChannelInboundHandlerAdapter {
    /*
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("/setu/?apikey=873602305f3d30bd0f5451&r18=1");
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                uri.toASCIIString());
        request.headers().set(HttpHeaderNames.HOST, "api.lolicon.app");
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        System.out.println(request);
        ctx.writeAndFlush(request);
    }
    */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg ->" + msg);
        if (msg instanceof FullHttpResponse) {
            HttpResponseStatus httpResponseStatus = ((FullHttpResponse) msg).status();
            FullHttpResponse response = (FullHttpResponse) msg;
            ByteBuf buf = response.content();
            String result = buf.toString(CharsetUtil.UTF_8);
            System.out.println(result);
        }
        ReferenceCountUtil.release(msg);
        // ctx.channel().close();

    }

}
