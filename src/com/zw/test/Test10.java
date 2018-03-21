package com.zw.test;

public class Test10 extends Test09 {
    public Test10() {
        System.out.println("Test10 init");
    }

    static {
        System.out.println("static code Test10");
    }

    public void sys() {
        System.out.println("sys");
    }

    public static void hello() {
        System.out.println("hello");
    }
}