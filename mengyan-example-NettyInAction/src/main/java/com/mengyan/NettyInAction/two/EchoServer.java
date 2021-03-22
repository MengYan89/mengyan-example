package com.mengyan.NettyInAction.two;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Echo服务端代码
 */
public class EchoServer {

    /**
     * 2-1 EchoServerHandler
     */
    // 可以被多个Channel安全的共享
    @ChannelHandler.Sharable
    public class EchoServerHandler extends ChannelInboundHandlerAdapter {

        /**
         * 每个消息传入都会调用
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf)msg;
            System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
            // 将接受的消息写给发送者，不冲刷出站消息(将消息加入出站缓冲区但不进行发送)
            ctx.write(in);
        }

        /**
         * 读取当前批量读取中的最后一条消息（当channelRead读取完最后一条消息后调用）
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            // 将消息发送至远程节点,并关闭Channel
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                    .addListener(ChannelFutureListener.CLOSE);
        }

        /**
         * 读取操作期间有异常抛出会调用
         * @param ctx
         * @param cause
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // 打印异常栈跟踪
            cause.printStackTrace();
            // 关闭该channel
            ctx.close();
        }
    }
}
