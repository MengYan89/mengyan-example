package com.mengyan.mirai.listener.special;

import com.mengyan.mirai.listener.EventHandlerListener;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 监听特殊消息
 */
@EventHandlerListener
public class SpecialMessageEventHandlers extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        // super.handleException(context, exception);
    }


    /**
     * 戳一戳消息
     * @param event
     * @return
     * @throws Exception
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull NudgeEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }
}
