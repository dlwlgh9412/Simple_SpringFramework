package com.coapgo.simple.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SimpleAnnotation
@Target(ElementType.TYPE) // 클래스 레벨
@Retention(RetentionPolicy.RUNTIME) // 런타임 유지
public @interface SimpleService {
}
