package com.mengyan.mirai.listener.group;

import com.mengyan.mirai.listener.EventHandlerListener;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;

/**
 * Bot与禁言相关的监听
 */
@EventHandlerListener
public class GroupMuteEventHandlers extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        // super.handleException(context, exception);
    }

    /**
     * bot被禁言
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull BotMuteEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println("被禁言:"+event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * bot禁言被解除
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull BotUnmuteEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println("禁言被解除:"+event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 群成员被禁言
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberMuteEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println("成员被禁言:"+event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 群成员禁言被解除
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberUnmuteEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println("群成员禁言被解除:"+event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

}
