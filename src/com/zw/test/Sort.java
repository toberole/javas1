package com.zw.test;


public class Sort {
    public static void bubbleSort(int array[]) {
        if (null != array) {
            for (int i = 0; i < array.length - 1; i++) {
                boolean flag = false;
                for (int j = array.length - 1; j > i; j--) {
                    if (array[j] < array[j - 1]) {
                        int temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                        flag = true;
                    }
                }

                if (!flag) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3, 1, 5, 2, 7, 3, 9};

        bubbleSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
