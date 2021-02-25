package com.mengyan.mirai.listener;

import java.lang.annotation.*;


/**
 * 用于标记Handler的注解类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventHandlerListener {
    /**
     * 实例化Handler时是否为单例
     * 默认为true 单例
     * @return
     */
    boolean singleton() default true;
}
