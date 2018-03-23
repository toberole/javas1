package com.zw.test;

public class Test11 {
    public static void main(String[] args) {
        //  Test10.hello();
        System.out.println("====================");

        Test10 test10 = new Test10();

        int arr[] = new int[]{1, 3, 5, 6, 7, 8, 31};
        int t = 10;

        int i1 = 0;
        int i2 = arr.length - 1;

        for (; i1 < i2; ) {
            if (arr[i1] + arr[i2] == t) {
                System.out.println("i1: " + i1 + " i2: " + i2);
                break;
            } else if (arr[i1] + arr[i2] < t) {
                i1++;
            } else if (arr[i1] + arr[i2] > t) {
                i2--;
            }
        }
    }
}
