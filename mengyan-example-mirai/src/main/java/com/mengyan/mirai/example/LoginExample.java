package com.mengyan.mirai.example;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.File;

/**
 * mirai框架登录样例
 */
public class LoginExample {

    private static final Integer qq = QQProperties.getQQ();
    private static final String pwd = QQProperties.getPwd();
    public static void main (String[] args) {
        login(qq, pwd);
    }

    public static void login(Integer qq, String pwd) {
        // 使用案例NewBotExample构建一个bot
        Bot bot = NewBotExample.newBot(qq, pwd);
        // 使用login方法可进行登录
        bot.login();
    }

    public static void login() {
        // 使用案例NewBotExample构建一个bot
        Bot bot = NewBotExample.newBot(qq, pwd);
        // 使用login方法可进行登录
        bot.login();
    }

}

