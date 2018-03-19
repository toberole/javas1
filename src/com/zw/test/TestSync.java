package com.zw.test;

import java.lang.String;
import java.util.concurrent.locks.ReentrantLock;

public class TestSync {
    private volatile static int i = 0;

    private static ReentrantLock lock = new ReentrantLock();

    private static Object object = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (object){
                        try {
                            lock.lock();
                            System.out.println("Thread1: " + i);
                            i++;
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        System.out.println("Thread2: " + i);
                        i++;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
    }
}
