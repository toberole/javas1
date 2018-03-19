package com.zw.test;


public class Sort {
    /**
     * 基本思想：两个数比较大小，较大的数下沉，较小的数冒起来。
     * <p>
     * 过程：
     * <p>
     * 比较相邻的两个数据，如果第二个数小，就交换位置。
     * 从后向前两两比较，一直到比较最前两个数据。最终最小数被交换到起始的位置，
     * 这样第一个最小数的位置就排好了
     * 继续重复上述过程，依次将第2.3...n-1个最小数排好位置。
     * <p>
     * <p>
     * 平均时间复杂度：O(n2)
     *
     * @param array
     */
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

    /**
     * 基本思想：
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
     * 。。。
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     * <p>
     * <p>
     * 平均时间复杂度：O(n2)
     *
     * @param array
     */
    public static void select_sort(int array[]) {
        if (array != null) {
            for (int i = 0; i < array.length - 1; i++) {
                int min_index = i;
                for (int j = i; j < array.length; j++) {
                    if (array[j] < array[min_index]) {
                        min_index = j;
                    }
                }

                if (min_index != i) {
                    int temp = array[i];
                    array[i] = array[min_index];
                    array[min_index] = temp;
                }
            }
        }
    }

    /**
     * 基本思想：
     * 在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，
     * 使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     * <p>
     * 平均时间复杂度：O(n2)
     *
     * @param array
     */
    public static void insert_sort(int array[]) {
        if (null != array) {
            for (int i = 0; i < array.length - 1; i++) {
                boolean flag = false;
                for (int j = i + 1; j > 0; j--) {
                    if (array[j] < array[j - 1]) {
                        int temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                        flag = true;
                    }

                    if (!flag) {
                        break;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3, 1, 5, 2, 7, 3, 9};

        // bubbleSort(arr);
        // select_sort(arr);
        insert_sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
