package com.coapgo.simple.springboot.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SimpleProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println();
        return null;
    }
}
