package com.zw.test;


import java.util.concurrent.atomic.AtomicInteger;

public class Plate1 {
    public volatile static int count = 0;

    public static void main(String[] args) {
        Plate1 plate = new Plate1();

        for (int i = 0; i < 3; i++) {
            new ThreadGet(plate).start();
            new ThreadGet(plate).start();
            new ThreadGet(plate).start();
            new ThreadGet(plate).start();
            new ThreadGet(plate).start();

            new ThreadPut(plate).start();
            new ThreadPut(plate).start();
            new ThreadPut(plate).start();
            new ThreadPut(plate).start();
            new ThreadPut(plate).start();
        }
    }

    public synchronized void put() {
        while (!(count < 3)) {
            try {
                this.wait();

                System.out.println("put wait");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        count++;
        System.out.println("放入了一个苹果 " + count);
        this.notifyAll();
    }

    public synchronized void get() {
        while (!(count > 0)) {
            try {
                this.wait();

                System.out.println("get wait");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        AtomicInteger integer = new AtomicInteger(0);
        integer.getAndIncrement();
        count--;
        System.out.println("拿出了一个苹果 " + count);
        this.notifyAll();
    }

    public static class ThreadGet extends Thread {
        private Plate1 plate;

        public ThreadGet(Plate1 plate) {
            this.plate = plate;
        }

        @Override
        public void run() {
            plate.get();
        }
    }

    public static class ThreadPut extends Thread {
        private Plate1 plate;

        public ThreadPut(Plate1 plate) {
            this.plate = plate;
        }

        @Override
        public void run() {
            plate.put();
        }
    }
}
