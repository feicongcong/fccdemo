package com.example.jdk8.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: CC.F
 * @Date: 12:50 2018/12/5
 */
//Annotation适用的程序元素
@Target({ElementType.TYPE,ElementType.FIELD})
//Annotation的有效范围
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    //Annotation只有一个成员时通常命名成value
    String value() default "默认值";
    int num() default 0;
}