package com.mengyan.NettyInAction.j;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * 10-10 CombinedChannelDuplexHandler<I,O>
 */
public class CombineByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
    // 将委托传递给父类
    public CombineByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }

}
