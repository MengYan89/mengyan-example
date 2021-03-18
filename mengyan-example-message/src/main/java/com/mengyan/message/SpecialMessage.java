package com.mengyan.message;

/**
 * 特殊消息
 */
public class SpecialMessage extends MyMessage<MessageType> {

    public SpecialMessage(MessageType content) {
        super(content);
    }

    public MessageType getType() {
        return this.content;
    }

    /**
     * 特殊消息本体枚举类
     * 特殊消息由于没有实际内容使用只需要一个对象对应
     */
    public enum SpecialMessageInstance {
        AT_ALL(new SpecialMessage(MessageType.AT_ALL)),
        DICE(new SpecialMessage(MessageType.DICE));
        public final SpecialMessage message;

        SpecialMessageInstance(SpecialMessage message) {
            this.message = message;
        }
    }
}
