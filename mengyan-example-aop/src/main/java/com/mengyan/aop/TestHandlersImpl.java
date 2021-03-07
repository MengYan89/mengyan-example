package com.mengyan.aop;

public interface TestHandlersImpl {
    @EventHandlerProxy
    Object onMessage(Object event) throws Exception;
}
