package com.zw.test.pattern;

/**
 * 懒汉式
 */
public class Singleton1 {
    private static Singleton1 instance = null;

    public static Singleton1 getInstance() {
        if (null == instance) {
            synchronized (Singleton1.class) {
                if (null == instance) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }

    public static synchronized Singleton1 getInstatnce1() {
        if (null == instance) {
            instance = new Singleton1();
        }

        return instance;
    }

    private Singleton1(){

    }
}
