package com.mengyan.aop;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        TestHandlersImpl testHandlers1 = TestHandlers.class.newInstance();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestHandlers.class);
        enhancer.setCallback(new HandlerProxy());
        TestHandlersImpl testHandlers2 = (TestHandlersImpl) enhancer.create();

        TestHandlersImpl testHandlers3 = (TestHandlersImpl) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{TestHandlersImpl.class}, new JDKHandlerProxy((TestHandlersImpl) TestHandlers.class.newInstance()));

        Method[] methods =testHandlers1.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if("onMessage".equals(method.getName())) {
                EventHandlerProxy eventHandlerProxy = method.getAnnotation(EventHandlerProxy.class);
                if (eventHandlerProxy == null) {
                    System.out.println("prototype no");
                } else {
                    System.out.println("prototype yes");
                }
            }

        }

        methods =testHandlers2.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if("onMessage".equals(method.getName())) {
                EventHandlerProxy eventHandlerProxy = method.getAnnotation(EventHandlerProxy.class);
                if (eventHandlerProxy == null) {
                    System.out.println("proxy no");
                } else {
                    System.out.println("proxy yes");
                }
            }
        }

        methods =testHandlers3.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if("onMessage".equals(method.getName())) {
                EventHandlerProxy eventHandlerProxy = method.getAnnotation(EventHandlerProxy.class);
                if (eventHandlerProxy == null) {
                    System.out.println("proxy2 no");
                } else {
                    System.out.println("proxy2 yes");
                }
            }
        }



    }
}
