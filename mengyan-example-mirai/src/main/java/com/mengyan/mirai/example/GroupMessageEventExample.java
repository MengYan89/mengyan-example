package com.mengyan.mirai.example;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * mirai框架监听群消息
 */
public class GroupMessageEventExample {
    public static void main(String[] args) {
        Bot bot = LoginExample.login();
        if(bot.isOnline()) {
            // 在EventChannel监听事件
            bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event -> {
                System.out.println(event.getGroup().getId()+":"+event.getMessage());
            });
            // 使用 @EventHandler 注解
            bot.getEventChannel().registerListenerHost(new EventHandlers());
        }
        while (true) {

        }
    }

    public static class EventHandlers extends SimpleListenerHost {
        @Override
        public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
            super.handleException(context, exception);
        }

        @NotNull
        @EventHandler
        public ListeningStatus onMessage(@NotNull GroupMessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
            System.out.println(event.getGroup().getId()+":"+event.getMessage());
            return ListeningStatus.LISTENING; // 表示继续监听事件
            // return ListeningStatus.STOPPED; // 表示停止监听事件
        }
    }
}
