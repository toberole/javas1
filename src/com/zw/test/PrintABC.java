package com.zw.test;

public class PrintABC {
    // 作为一个控制的变量
    private volatile static int flag = 1;

    public static void main(String[] args) {
        PrintABC abc = new PrintABC();

        for (int i = 0; i < 10; i++) {
            new ThreadA(abc).start();
            new ThreadB(abc).start();
            new ThreadC(abc).start();
        }
    }

    public synchronized void printA() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.print("A");

        flag = 2;
        this.notifyAll();
    }

    public synchronized void printB() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.print("B");

        flag = 3;
        this.notifyAll();
    }

    public synchronized void printC() {
        while (flag != 3) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("C");

        flag = 1;

        this.notifyAll();
    }

    public static class ThreadA extends Thread {
        private PrintABC abc;

        public ThreadA(PrintABC abc) {
            this.abc = abc;
        }

        @Override
        public void run() {
            abc.printA();
        }
    }

    public static class ThreadB extends Thread {
        private PrintABC abc;

        public ThreadB(PrintABC abc) {
            this.abc = abc;
        }

        @Override
        public void run() {
            abc.printB();
        }
    }

    public static class ThreadC extends Thread {
        private PrintABC abc;

        public ThreadC(PrintABC abc) {
            this.abc = abc;
        }

        @Override
        public void run() {
            abc.printC();
        }
    }
}
