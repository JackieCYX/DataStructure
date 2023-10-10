package com.example.sort.insert.insertsort;

import java.util.Random;

/**
 * 直接插入排序
 * 时间复杂度：最坏 O（N^2），最好 O（N）
 * 空间复杂度：O（1）
 * 稳定性：稳定
 */
public class InsertSort {
    public static void main(String[] args) {
        // 使用random随机生成较大数组
        int[] array = new int[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt();
        }
        long startTime = System.currentTimeMillis();
        insertSort(array);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void insertSort(int[] array) {
        if (array == null) {
            return;
        }
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                    array[j] = temp;
                } else {
                    break;
                }
            }
        }
    }
}
