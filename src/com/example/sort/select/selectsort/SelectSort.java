package com.example.sort.select.selectsort;

import java.util.Random;

/**
 * 选择排序
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class SelectSort {
    public static void main(String[] args) {
        // 使用random随机生成较大数组
        int[] array = new int[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt();
        }

        // 使用未优化的冒泡排序
        long startTime = System.currentTimeMillis();
        selectSort(array);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime);

    }

    /**
     * 选择排序
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            int j = i + 1;
            for (; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
        }
    }

    private static void swap(int[] array, int minIndex, int i ) {
        int temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
    }
}
