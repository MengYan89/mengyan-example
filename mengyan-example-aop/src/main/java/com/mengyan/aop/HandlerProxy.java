package com.mengyan.aop;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Handler的代理类
 */
public class HandlerProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try {
            System.out.println("before cglib");
            result = methodProxy.invokeSuper(o,objects);
        }catch (Exception e){
            throw e;
        }finally {
            System.out.println("after cglib");
        }
        return result;
    }
}
