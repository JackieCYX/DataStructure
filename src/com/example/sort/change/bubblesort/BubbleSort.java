package com.example.sort.change.bubblesort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {42, 17, 56, 89, 23, 5, 74, 33, 61, 10, 29, 92, 70, 84, 68, 11, 39, 95, 77, 6};
        // 使用未优化的冒泡排序
        long startTime = System.currentTimeMillis();
        bubbleSort(array);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime);

        // 使用优化后的冒泡排序
        int[] array1 = {42, 17, 56, 89, 23, 5, 74, 33, 61, 10, 29, 92, 70, 84, 68, 11, 39, 95, 77, 6};
        long startTime1 = System.currentTimeMillis();
        bubbleSort_1(array1);
        long endTime1 = System.currentTimeMillis();
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime1);

    }

    /**
     * 冒泡排序(未优化)
     * 时间复杂度:最坏 O(N*N) 最好 O(N)
     * 时间复杂度:O(1)
     * 稳定性:稳定
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序（优化）
     */
    public static void bubbleSort_1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean mark = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    mark = true;
                }
            }
            if (!mark){
                return;
            }
        }

    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
