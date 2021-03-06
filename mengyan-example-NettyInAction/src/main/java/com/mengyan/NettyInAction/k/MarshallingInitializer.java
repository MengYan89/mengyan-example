package com.mengyan.NettyInAction.k;

import io.netty.channel.*;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import java.io.Serializable;

/**
 * 11-13 使用JBoss Marshalling
 */
public class MarshallingInitializer extends ChannelInitializer<Channel> {
    private final MarshallerProvider marshallerProvider;
    private final UnmarshallerProvider unmarshallerProvider;

    public MarshallingInitializer(MarshallerProvider marshallerProvider, UnmarshallerProvider unmarshallerProvider) {
        this.marshallerProvider = marshallerProvider;
        this.unmarshallerProvider = unmarshallerProvider;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 添加MarshallingDecoder以将ByteBuf转换为POJO
        pipeline.addLast(new MarshallingDecoder(unmarshallerProvider));
        // 添加MarshallingEncoder以将POJO转换为ByteBuf
        pipeline.addLast(new MarshallingEncoder(marshallerProvider));
        // 添加ObjectHandler,以处理普通的实现了Serializable接口的POJO
        pipeline.addLast(new ObjectHandler());
    }

    public static final class ObjectHandler extends SimpleChannelInboundHandler<Serializable> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, Serializable serializable) throws Exception {
            // Do something
        }
    }
}
