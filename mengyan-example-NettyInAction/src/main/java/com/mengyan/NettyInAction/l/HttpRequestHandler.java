package com.mengyan.NettyInAction.l;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 12-1 HttpRequestHandler
 * 扩展SimpleChannelInboundHandler以处理FullHttpRequest消息
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUri;
    private static File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:")? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        // 如果请求了WebSocket协议升级,则添加引用计数(调用retain()方法)并将它传递给下一个ChannelInboundHandler
        if (wsUri.equalsIgnoreCase(fullHttpRequest.getUri())) {
            channelHandlerContext.fireChannelRead(fullHttpRequest.retain());
        } else {
            // 处理 100 Continue请求以符合 HTTP 1.1 规范
            if (HttpHeaders.is100ContinueExpected(fullHttpRequest)) {
                send100Continue(channelHandlerContext);
            }
            // 读取index.html
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultHttpResponse(
                    fullHttpRequest.protocolVersion(), HttpResponseStatus.OK
            );
            response.headers().set(
                    HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8"
            );
            boolean keepAlive = HttpHeaders.isKeepAlive(fullHttpRequest);
            // 如果请求了keep-alive则添加所需要的HTTP头消息
            if (keepAlive) {
                response.headers().set(
                        HttpHeaders.Names.CONTENT_LENGTH, file.length()
                );
                response.headers().set(
                        HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE
                );
            }
            // 将HttpResponse写到客户端
            channelHandlerContext.write(response);
            // 将index.html写到客户端
            if (channelHandlerContext.pipeline().get(SslHandler.class) == null) {
                channelHandlerContext.write(new DefaultFileRegion(
                        file.getChannel(), 0, file.length()
                ));
            } else {
                channelHandlerContext.write(new ChunkedNioFile(file.getChannel()));
            }
            // 写LastHttpContent并冲刷至客户端
            ChannelFuture future = channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if (!keepAlive) {
                // 如果没有请求Keep-alive则在写操作完成后关闭Channel
                future.addListener(ChannelFutureListener.CLOSE);
            }

        }
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE
        );
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
