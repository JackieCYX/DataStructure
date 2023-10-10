package com.example.sort.insert.shellsort;

import java.util.Random;

/**
 * 希尔排序
 * 时间复杂度：O（N^1.3~N^1.5）
 * 空间复杂度：O（1）
 * 稳定性：不稳定
 */
public class ShellSort {
    public static void main(String[] args) {
        // 使用random随机生成较大数组
        int[] array = new int[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt();
        }
        long startTime = System.currentTimeMillis();
        shellSort(array);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime);

        for (int j : array) {
            System.out.print(j + " ");
        }

    }

    public static void shellSort(int[] array) {
        int gap = array.length;
        while (gap > 1) {
            gap /= 2;
            shell(array, gap);
        }
    }

    public static void shell(int[] array, int gap) {
        if (array == null) {
            return;
        }
        int temp = 0;
        for (int i = gap; i < array.length; i++) {
            temp = array[i];
            for (int j = i - gap; j >= 0; j -= gap) {
                if (array[j] > temp) {
                    array[j + gap] = array[j];
                    array[j] = temp;
                } else {
                    break;
                }
            }
        }
    }
}
