package com.coapgo.simple.springboot.context;

import com.coapgo.simple.springboot.annotation.SimpleAnnotation;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SimpleClassScanner {
    public List<Class<?>> scanClass(String packageName) throws ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        if (resource != null) {
            scanDirectory(new File(resource.getFile()), packageName, classList);
        }

        return classList;
    }

    private void scanDirectory(File directory, String packageName, List<Class<?>> classList) throws ClassNotFoundException {
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    scanDirectory(file, packageName + "." + file.getName(), classList);
                } else if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                    Class<?> clazz = Class.forName(className);

                    if (!clazz.isAnnotation() && isDeclaredSimpleAnnotation(clazz)) {
                        System.out.println("Registered Class: " + clazz.getName());
                        classList.add(clazz);
                    }
                }
            }
        }
    }

    private boolean isDeclaredSimpleAnnotation(Class<?> clazz) {
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.isAnnotationPresent(SimpleAnnotation.class)) {
                return true;
            }
        }

        return false;
    }
}
