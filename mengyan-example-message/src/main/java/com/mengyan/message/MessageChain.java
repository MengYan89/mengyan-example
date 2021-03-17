package com.mengyan.message;

import java.util.ArrayList;
import java.util.List;

/**
 * 由消息组成的消息链
 */
public class MessageChain extends MyMessage<List<MyMessage>> {

    public MessageChain(MyMessage content) {
        super(new ArrayList<MyMessage>());
        this.content.add(content);
    }

    public MessageChain(List<MyMessage> content) {
        super(content);
    }

    public MessageType getType() {
        return MessageType.MESSAGE_CHAIN;
    }

    public MessageChain add(MyMessage message) {
        this.content.add(message);
        return this;
    }

}
