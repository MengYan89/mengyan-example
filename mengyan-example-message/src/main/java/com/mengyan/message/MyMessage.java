package com.mengyan.message;

/**
 * 消息基类，每个消息类型都需要继承
 */
public abstract class MyMessage<T> {

    public MyMessage(T content) {
        this.content = content;
    }

    // 消息内容本体
    protected final T content;

    /**
     * 返回这个消息的类型，每个消息类必须实现
     * @return
     */
    abstract MessageType getType();

    /**
     * 返回消息内容本体
     * @return
     */
    public T getContent() {
        return content;
    }

}
