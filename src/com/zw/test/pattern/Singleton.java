package com.zw.test.pattern;

/**
 * 饿汉式
 */
public class Singleton {
    private volatile static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    private Singleton() {

    }
}
