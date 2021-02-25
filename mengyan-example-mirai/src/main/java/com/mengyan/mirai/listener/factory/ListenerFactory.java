package com.mengyan.mirai.listener.factory;

import com.mengyan.mirai.listener.EventHandlerListener;
import com.mengyan.mirai.util.ClassUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.event.SimpleListenerHost;

import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 构建监听类的工厂类
 */
public class ListenerFactory {

    private ListenerFactory() {

    }

    // 缓存实例化的单例handlers
    private static final ConcurrentMap<Class, SimpleListenerHost> map = new ConcurrentHashMap<>();

    // 缓存不进行单例的class
    private static final Vector<Class<? extends SimpleListenerHost>> notSingletonHandlerClass = new Vector<>();

    private static  boolean load = false;
    /**
     * 根据class获取实例
     * @param type
     * @return
     */
    public static SimpleListenerHost getSingleton(Class<? extends SimpleListenerHost> type) {
        SimpleListenerHost simpleListener = map.get(type);
        try {
            // 缓存中没有的情况下
            if (simpleListener == null) {
                // 判断是否被注解标注
                if (type.isAnnotationPresent(EventHandlerListener.class)) {
                    // 如果注解中的属性标注为非单例则实例化一个全新的对象返回
                    if (!type.getAnnotation(EventHandlerListener.class).singleton()) {
                        return type.newInstance();
                    }
                }
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

    /**
     * 加载标注了EventHandlerListener注解的Handler
     * 将标注为单例的先实例化保存
     * 将未标注单例的class保存用于实例化
     */
    public static synchronized void loadHandlers() throws IllegalAccessException, InstantiationException {
        // 获得指定包下的class
        Set<Class<?>> handlerClass = ClassUtil.getClasses("com.mengyan.mirai.listener");
        for (Class clazz : handlerClass) {
            // 判断这个class是否继承SimpleListenerHost
            if (clazz.isAssignableFrom(SimpleListenerHost.class)) {
                // 是否被EventHandlerListener标记
                if (clazz.isAnnotationPresent(EventHandlerListener.class)) {
                    EventHandlerListener annotation = (EventHandlerListener)clazz.getAnnotation(EventHandlerListener.class);
                    // 根据注解属性判断如何处理
                    if (annotation.singleton()) {
                        SimpleListenerHost listenerHandler = (SimpleListenerHost)clazz.newInstance();
                        map.put(clazz, listenerHandler);
                    } else {
                        notSingletonHandlerClass.add(clazz);
                    }
                }
            }
        }
        load = true;
    }

    /**
     * 对bot进行Listener的构建
     * @param bot
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void assembleListenerForBot(Bot bot) throws InstantiationException, IllegalAccessException {
        if (!load) {
            // 没有进行加载
            loadHandlers();
        }
        for (Map.Entry<Class, SimpleListenerHost> entry : map.entrySet()) {
            // TODO 没有对coroutineContext进行设置
            bot.getEventChannel().registerListenerHost(entry.getValue());
        }
        for (Class clazz : notSingletonHandlerClass) {
            // TODO 没有对coroutineContext进行设置
            bot.getEventChannel().registerListenerHost((ListenerHost) clazz.newInstance());
        }

    }


}
