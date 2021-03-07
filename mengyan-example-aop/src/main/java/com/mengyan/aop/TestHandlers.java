package com.mengyan.aop;

public class TestHandlers implements TestHandlersImpl {

    @EventHandlerProxy
    public Object onMessage(Object event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        // event.subject.sendMessage("received");
        System.out.println(event);
        return null; // 表示继续监听事件
        // return ListeningStatus.STOPPED; // 表示停止监听事件
    }
}
