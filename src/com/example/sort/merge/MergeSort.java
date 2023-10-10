package com.example.sort.merge;

import java.util.Random;

/**
 * 归并排序
 * 时间复杂度：O（N^logN）
 * 空间复杂度：O（N）
 * 稳定性：稳定
 */
public class MergeSort {
    public static void main(String[] args) {
        // 使用random随机生成较大数组
        int[] array = new int[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt();
        }
        long startTime = System.currentTimeMillis();
        mergeSort(array);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void mergeSort(int[] array) {
        divid(array, 0, array.length - 1);
    }

    private static void divid(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >>> 1;
        divid(array, left, mid);
        divid(array, mid + 1, right);
        merge(array, left, right, mid);
    }

    private static void merge(int[] array, int left, int right, int mid) {
        int l1 = left;
        int l2 = mid + 1;
        int[] temp = new int[right - left + 1];
        int i = 0;
        while (l1 <= mid && l2 <= right) {
            if (array[l1] <= array[l2]) {
                temp[i++] = array[l1++];
            }
            if (array[l2] <= array[l1]) {
                temp[i++] = array[l2++];
            }
        }
        while (l1 <= mid) {
            temp[i++] = array[l1++];
        }
        while(l2 <= right) {
            temp[i++] = array[l2++];
        }
        for (int j = 0; j < temp.length; j++) {
            array[j + left] = temp[j];
        }

    }
}
