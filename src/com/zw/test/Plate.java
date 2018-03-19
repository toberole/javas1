package com.zw.test;


public class Plate {
    private volatile static int plate = 0;//声明一个变量,代表盘子，可以放入和拿出

    //放入一个苹果,使用synchronized关键字
    public synchronized void putApple() throws InterruptedException {
        while (plate == 1) {
            this.wait();//如果有苹果，不能再放了，就等待
        }
        System.out.println("放入apple");
        plate = 1;
        this.notifyAll();//放入后，唤醒等待的线程
    }

    //拿出一个苹果,使用synchronized关键字
    public synchronized void getApple() throws InterruptedException {
        while (plate == 0) {
            this.wait();//如果盘子为空，则不能取，等待放入苹果才能取
        }
        System.out.println("拿出苹果");
        plate = 0;
        this.notifyAll();//取出后，唤醒等待线程
    }

    public static void main(String[] args) {
        Plate plate = new Plate();
        for (int i = 0; i < 20; i++) {//put和get各执行20次
            new getApple(plate).start();
            new putApple(plate).start();
        }
    }
}

//拿出苹果的线程
class getApple extends Thread {
    private Plate plate;

    public getApple(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {
        try {
            plate.getApple();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//放入苹果的线程
class putApple extends Thread {
    private Plate plate;

    public putApple(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {
        try {
            plate.putApple();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
