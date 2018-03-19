package com.zw.test;

public class PrintQu implements Runnable {
    private Num num;

    public PrintQu(Num num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (num.i < 100) {
            synchronized (num)/* 必须要用一把锁对象，这个对象是num*/ {
                if (!num.flag) {
                    try {
                        num.wait();  //操作wait()函数的必须和锁是同一个
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("偶数-----" + num.i);
                    num.i++;
                    num.flag = false;
                    num.notify();
                }
            }
        }
    }
}
