package com.mengyan.NettyInAction.k;

import com.google.protobuf.MessageLite;
import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * 11-14 使用protobuf
 */
public class ProtoBufInitializer extends ChannelInitializer<Channel> {
    private final MessageLite lite;

    public ProtoBufInitializer(MessageLite lite) {
        this.lite = lite;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 添加ProtobufVarint32FrameDecoder以分隔帧
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        // 添加ProtobufEncoder以处理消息的编码
        pipeline.addLast(new ProtobufEncoder());
        // 添加ProtobufDecoder以解码消息
        pipeline.addLast(new ProtobufDecoder(lite));
        // 添加ObjectHandler处理解码消息
        pipeline.addLast(new ObjectHandler());
    }

    public static final class ObjectHandler extends SimpleChannelInboundHandler<Object> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
            // Do something with the object
        }
    }
}
