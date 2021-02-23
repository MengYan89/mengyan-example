package com.mengyan.mirai.listener.factory;

import net.mamoe.mirai.event.SimpleListenerHost;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 构建监听类的工厂类
 */
public class ListenerFactory {
    private static final ConcurrentMap<Class, SimpleListenerHost> map = new ConcurrentHashMap<>();

    public static SimpleListenerHost getSingleton(Class<? extends SimpleListenerHost> type) {
        SimpleListenerHost simpleListener = map.get(type);
        try {
            if (simpleListener == null) {
                synchronized (map) {
                    simpleListener = type.newInstance();
                    map.put(type, simpleListener);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return simpleListener;
    }
}
