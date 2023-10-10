package com.example.sort.change.quicksort;

import java.util.Random;

/**
 * 快排
 * 时间复杂度：最好情况：O(N^log₂N)
 * 最坏情况：有序或逆序：O（N^N） 数据量大时有可能栈溢出异常
 * 空间复杂度：最好情况：O(logN)
 * 最坏情况：有序或逆序：O（N）
 * 稳定性：不稳定
 */
public class QuickSort {
    public static void main(String[] args) {
        // 使用random随机生成较大数组
        int[] array = new int[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt();
        }

        // 使用未优化的冒泡排序
        long startTime = System.currentTimeMillis();
        quickSort(array);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime);
    }

    public static void quickSort(int[] array) {
        quick(array, 0, array.length - 1);
    }

    private static void quick(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        // 找到基准（枢轴）
        int pivot = partition(array, start, end);
        quick(array, start, pivot - 1);
        quick(array, pivot + 1, end);
    }

    // 最容易理解的挖坑法
    private static int partition(int[] array, int left, int right) {
        int temp = array[left];
        while (left < right) {
            while (left < right && array[right] >= temp) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left] <= temp) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = temp;
        return left;
    }
}
