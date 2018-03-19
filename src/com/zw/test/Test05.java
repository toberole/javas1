package com.zw.test;

import sun.misc.ProxyGenerator;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test05 {
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    public static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void test() {
        readLock.lock();

       // ProxyGenerator.generateProxyClass()

        int i = 11;
        int j = 11;
        int n = i + j;

        readLock.unlock();
    }

    public static void main(String[] args) {
        System.out.println("Hello Test05");
    }
}
