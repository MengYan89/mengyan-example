package com.mengyan.message;

public class TextMessage extends MyMessage<String> {


    public TextMessage(String content) {
        super(content);
    }

    public MessageType getType() {
        return MessageType.TEXT;
    }

}
