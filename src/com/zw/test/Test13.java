package com.zw.test;

public class Test13 {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("=== Hello World ===");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("==== Hello World ====");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("===== Hello World =====");
            }
        });
    }
}
