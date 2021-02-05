package com.mengyan.mirai.example;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * mirai框架监听群消息
 */
public class GroupMessageEventExample {
    public static void main(String[] args) {
        Bot bot = LoginExample.login();
        if(bot.isOnline()) {
            bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event -> {
                System.out.println(event.getGroup().getId()+":"+event.getMessage());
            });
        }
        while (true) {

        }
    }
}
