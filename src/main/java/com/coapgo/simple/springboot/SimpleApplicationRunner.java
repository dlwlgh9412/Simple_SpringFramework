package com.coapgo.simple.springboot;

import com.coapgo.simple.springboot.context.SimpleClassScanner;

import java.util.List;

public class SimpleApplicationRunner {
    public static void run(Class<?> resoucreClass) throws ClassNotFoundException {
        String packageName = resoucreClass.getPackageName();

        SimpleClassScanner classScanner = new SimpleClassScanner();
        List<Class<?>> classes = classScanner.scanClass(packageName);
    }
}
