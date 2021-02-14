package com.mengyan.mirai.example;

import net.mamoe.mirai.message.data.*;

import java.util.LinkedList;

/**
 * 构造消息链
 */
public class NewMessageChainExample {

    public static void main(String[] args) {
        // 使用MessageUtils.newChain方式构造MessageChain
        // 同时也支持使用继承了Iterator接口的集合来构造
        MessageChain messages = MessageUtils.newChain(new PlainText("Hello"), Image.fromId("{f8f1ab55-bf8e-4236-b55e-955848d7069f}.png"));

        // 使用MessageChainBuilder构造
        messages = new MessageChainBuilder()
                .append(new PlainText("Hello"))
                .append(Image.fromId("{f8f1ab55-bf8e-4236-b55e-955848d7069f}.png"))
                .build();
        // 也可以在发送消息时使用从Message中继承的plus方法构建
        // sendMessage(new PlainText("Hello").plus(Image.fromId("{f8f1ab55-bf8e-4236-b55e-955848d7069f}.png")))

    }
}
