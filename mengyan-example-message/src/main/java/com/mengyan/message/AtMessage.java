package com.mengyan.message;

/**
 * AT某个人的消息
 */
public class AtMessage extends MyMessage<Long> {

    public AtMessage(Long content) {
        super(content);
    }

    public MessageType getType() {
        return MessageType.AT;
    }
}
