package com.mengyan.mirai.listener.power;

import com.mengyan.mirai.listener.EventHandlerListener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent;
import net.mamoe.mirai.event.events.FriendAddEvent;
import net.mamoe.mirai.event.events.FriendDeleteEvent;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 监听Bot与好友相关权限事件
 */
@EventHandlerListener
public class FriendPowerEventHandlers extends SimpleListenerHost {

    /**
     * 一个账号请求添加bot的好友
     * @param event
     * @return
     * @throws Exception
     */
    public ListeningStatus onMessage(@NotNull NewFriendRequestEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * bot成功添加了一个好友
     * @param event
     * @return
     * @throws Exception
     */
    public ListeningStatus onMessage(@NotNull FriendAddEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * bot删除了一个好友
     * @param event
     * @return
     * @throws Exception
     */
    public ListeningStatus onMessage(@NotNull FriendDeleteEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }
}
