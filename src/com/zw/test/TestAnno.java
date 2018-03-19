package com.zw.test;

import java.lang.String;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface TestAnno {
    // 定义一个value "属性"
    String value() default "db";

    // 定义一个count "属性"
    int count() default 0;
}
