package com.zw.test.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 *
 * Retention 默认级别是在class级别的
 */

@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IName {
    // 定义value属性 默认值是""
    String value() default "";

    int age() default 11;
}
