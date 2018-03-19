package com.zw.test;

import java.lang.String;

public class TestPrintNum {
    public static void main(String[] args) {
        Num num = new Num();
        PrintQu printQu = new PrintQu(num);
        PrintQi printQi = new PrintQi(num);

        Thread printQuThread = new Thread(printQu);
        Thread printQiThread = new Thread(printQi);

        printQiThread.start();
        printQuThread.start();
    }
}
