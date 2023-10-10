package com.example.sort.select.heapsort;

import java.util.Random;

/**
 * 堆排序
 * 时间复杂度：O（N*log₂N）
 * 空间复杂度：O（1）
 * 稳定性：不稳定
 */
public class HeapSort {
    public static void main(String[] args) {
        // 使用random随机生成较大数组
        int[] array = new int[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt();
        }
        long startTime = System.currentTimeMillis();
        heapSort(array);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("程序运行时间（毫秒）：" + elapsedTime);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void heapSort(int[] array) {
        // 根据数组建堆（重难点）
        createHeap(array);
        int len = array.length - 1;
        while (len > 0) {
            swap(array, 0, len);
            shiftDown(array, 0, len);
            len--;
        }
    }

    private static void createHeap(int[] array) {
        // 从最后一个树的根结点开始向下调整
        for (int parent = (array.length - 1 - 1) / 2
             ; parent >= 0; parent--) {
            shiftDown(array, parent, array.length);
        }
    }

    private static void shiftDown(int[] array, int parent, int length) {
        int child = parent * 2 + 1;
        while (child < length) {
            if (child + 1 < length && array[child] < array[child + 1]){
                child++;
            }
            if (array[child] > array[parent]) {
                swap(array, child, parent);
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
