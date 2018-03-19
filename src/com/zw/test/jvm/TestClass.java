package com.zw.test.jvm;

public class TestClass {
    public final static String TAG = "TestClass";

    private int m = 0;

    public int inc() {
        return m + 1;
    }

    public static void main(String[] args) {
        System.out.println("hello jvm");
    }
}
