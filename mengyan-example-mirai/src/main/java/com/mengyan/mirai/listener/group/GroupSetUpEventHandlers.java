package com.mengyan.mirai.listener.group;

import com.mengyan.mirai.listener.EventHandlerListener;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;


/**
 * 与bot所在群相关设置的监听
 */
@EventHandlerListener
public class GroupSetUpEventHandlers  extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        // super.handleException(context, exception);
    }

    /**
     * 一个账号申请入群的请求
     * TODO 应该需要机器人拥有管理员权限才可以获取到 待测试
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberJoinRequestEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 群设置做出改变的同一监听可以进行细化
     * 群名改变: GroupNameChangeEvent
     * 入群公告改变: GroupEntranceAnnouncementChangeEvent
     * 全员禁言状态改变: GroupMuteAllEvent
     * 匿名聊天状态改变: GroupAllowAnonymousChatEvent
     * 允许群员邀请好友加群状态改变: GroupAllowMemberInviteEvent
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull GroupSettingChangeEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 监听成员入群可以细化
     * 成员被邀请加入群: MemberJoinEvent.Invite
     * 成员主动加入群: MemberJoinEvent.Active
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberJoinEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 监听群员退群状况可以细化
     * 成员被踢出群: MemberLeaveEvent.Kick
     * 成员主动离开群: MemberLeaveEvent.Quit
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberLeaveEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 监听群名片改动
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberCardChangeEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 监听群头衔改动
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberSpecialTitleChangeEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }

    /**
     * 监听群成员权限改动
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MemberPermissionChangeEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }


}
