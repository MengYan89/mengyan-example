package com.mengyan.mirai.example;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * Mirai框架机器人监听机器人被邀请入群
 */
public class InvitedJoinGroupEventExample {
    public static void main(String[] args) {
        Bot bot = LoginExample.login();
        if(bot.isOnline()) {
            bot.getEventChannel().subscribeAlways(BotInvitedJoinGroupRequestEvent.class, event -> {
                // System.out.println(event.getGroup().getId()+":"+event.getMessage());
                // 同意入群邀请
                event.accept();
                // 忽视入群邀请
                event.ignore();
                // 获得群号
                event.getGroupId();
                // 获得群名
                event.getGroupName();
                // 获取邀请你的好友对象
                event.getInvitor();
                // 获取邀请你的好友账号
                event.getInvitorId();
                // 获取邀请人昵称
                event.getInvitorNick();
            });
        }
        while (true) {

        }
    }
}
