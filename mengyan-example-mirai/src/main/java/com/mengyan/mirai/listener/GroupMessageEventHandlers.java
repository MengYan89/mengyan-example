package com.mengyan.mirai.listener;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 监听QQ群消息的监听器
 */
public class GroupMessageEventHandlers extends SimpleListenerHost {


    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        // super.handleException(context, exception);
    }

    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull GroupMessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event.getMessage());
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }
}
