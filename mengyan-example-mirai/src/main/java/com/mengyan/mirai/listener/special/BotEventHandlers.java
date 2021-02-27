package com.mengyan.mirai.listener.special;

import com.mengyan.mirai.listener.EventHandlerListener;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;

/**
 * 与Bot状态相关的事件监听
 */
@EventHandlerListener
public class BotEventHandlers extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        // super.handleException(context, exception);
    }

    /**
     * Bot登录完成事件
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull BotOnlineEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }


    /**
     * Bot离线事件可细化
     * 主动: BotOfflineEvent.Active
     * 被挤下线: BotOfflineEvent.Force
     * 被服务器断开或因网络问题而掉线: BotOfflineEvent.Dropped
     * 服务器主动要求更换另一个服务器: BotOfflineEvent.RequireReconnect
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull BotOfflineEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }


    /**
     * Bot重新登录事件
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull BotReloginEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }


    /**
     * Bot头像修改事件
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull BotAvatarChangedEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * Bot昵称修改事件
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull BotNickChangedEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }


}
