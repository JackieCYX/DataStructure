package com.example.sort.insert.shellsort;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {42, 17, 56, 89, 23, 5, 74, 33, 61, 10, 29, 92, 70, 84, 68, 11, 39, 95, 77, 6};
        shellSort(array);
        for (int j : array) {
            System.out.print(j + " ");
        }

    }

    /**
     * 希尔排序
     * 时间复杂度：O（N^1.3~N^1.5）
     * 空间复杂度：O（1）
     * 稳定性：不稳定
     *
     * @param array
     */
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
