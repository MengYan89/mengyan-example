package com.mengyan.mirai.example;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;

import java.util.NoSuchElementException;

/**
 * mirai框架获取一个机器人的群
 */
public class GetGroupExample {
    public static void main(String[] args) {
        Bot bot = LoginExample.login();
        // 判断bot是否在线
        if (bot.isOnline()) {
            // 根据群号获取Group对象,获取失败会返回null
            Group group = bot.getGroup(910726832);

            // 根据群号获取Group对象,回去失败会抛出NoSuchElementException
            try {
                group = bot.getGroupOrFail(910726832);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }
}
