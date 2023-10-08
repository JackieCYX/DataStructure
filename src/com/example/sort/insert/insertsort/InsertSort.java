package com.example.sort.insert.insertsort;

/**
 * 直接插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {42, 17, 56, 89, 23, 5, 74, 33, 61, 10, 29, 92, 70, 84, 68, 11, 39, 95, 77, 6};
        insertSort(array);
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
