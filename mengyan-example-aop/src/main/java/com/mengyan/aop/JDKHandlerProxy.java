package com.mengyan.aop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKHandlerProxy implements InvocationHandler {

    private TestHandlersImpl handlers;

    public JDKHandlerProxy(TestHandlersImpl handlers) {
        this.handlers = handlers;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("before");
            //调用目标对象的方法，使用反射
            result = method.invoke(handlers, args);
        }catch (Exception e){
            throw e;
        }finally {
            System.out.println("after");
            return result;
        }

    }
}
