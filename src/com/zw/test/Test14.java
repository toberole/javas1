package com.zw.test;

/**
 * 生产者 消费者
 */
public class Test14 {
    public static volatile int count = 0;

    public synchronized void put() {
        while (count >= 1) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("放入了一个");

        count++;
        this.notifyAll();
    }

    public synchronized void get() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("取出一个了");
        count--;
        this.notifyAll();
    }

    public static void main(String[] args) {
        Test14 test14 = new Test14();
        new ProducerThread(test14).start();
        new ConsumeThread(test14).start();
        new ProducerThread(test14).start();
        new ConsumeThread(test14).start();
    }

    public static class ConsumeThread extends Thread {
        public Test14 test14;

        public ConsumeThread(Test14 test14) {
            this.test14 = test14;
        }

        @Override
        public void run() {
            test14.get();
        }
    }


    public static class ProducerThread extends Thread {
        public Test14 test14;

        public ProducerThread(Test14 test14) {
            this.test14 = test14;
        }

        @Override
        public void run() {
            test14.put();
        }
    }
}
