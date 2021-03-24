package com.mengyan.NettyInAction;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.SocketAddress;

/**
 * 用于生成一个虚拟的Channel以保证样例代码不进行报错
 */
public class ChannelSimulation {

    public static Channel create() {
        return new Channel() {
            public ChannelId id() {
                return null;
            }

            public EventLoop eventLoop() {
                return null;
            }

            public Channel parent() {
                return null;
            }

            public ChannelConfig config() {
                return null;
            }

            public boolean isOpen() {
                return false;
            }

            public boolean isRegistered() {
                return false;
            }

            public boolean isActive() {
                return false;
            }

            public ChannelMetadata metadata() {
                return null;
            }

            public SocketAddress localAddress() {
                return null;
            }

            public SocketAddress remoteAddress() {
                return null;
            }

            public ChannelFuture closeFuture() {
                return null;
            }

            public boolean isWritable() {
                return false;
            }

            public long bytesBeforeUnwritable() {
                return 0;
            }

            public long bytesBeforeWritable() {
                return 0;
            }

            public Unsafe unsafe() {
                return null;
            }

            public ChannelPipeline pipeline() {
                return null;
            }

            public ByteBufAllocator alloc() {
                return null;
            }

            public Channel read() {
                return null;
            }

            public Channel flush() {
                return null;
            }

            public ChannelFuture bind(SocketAddress socketAddress) {
                return null;
            }

            public ChannelFuture connect(SocketAddress socketAddress) {
                return null;
            }

            public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress1) {
                return null;
            }

            public ChannelFuture disconnect() {
                return null;
            }

            public ChannelFuture close() {
                return null;
            }

            public ChannelFuture deregister() {
                return null;
            }

            public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress1, ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture disconnect(ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture close(ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture deregister(ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture write(Object o) {
                return null;
            }

            public ChannelFuture write(Object o, ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture writeAndFlush(Object o, ChannelPromise channelPromise) {
                return null;
            }

            public ChannelFuture writeAndFlush(Object o) {
                return null;
            }

            public ChannelPromise newPromise() {
                return null;
            }

            public ChannelProgressivePromise newProgressivePromise() {
                return null;
            }

            public ChannelFuture newSucceededFuture() {
                return null;
            }

            public ChannelFuture newFailedFuture(Throwable throwable) {
                return null;
            }

            public ChannelPromise voidPromise() {
                return null;
            }

            public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
                return null;
            }

            public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
                return false;
            }

            public int compareTo(Channel o) {
                return 0;
            }
        };
    }
}
