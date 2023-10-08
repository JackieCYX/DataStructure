package com.example.search.basicsearch;

/**
 * 顺序查找
 * 核心：从0索引开始挨个往后查找
 */
public class BasicSearch {
    public static void main(String[] args) {
        int[] arr = {131, 127, 147, 81, 103, 23, 7, 79};
        int number = 82;
        System.out.println(basicSearch(arr, number));
    }

    /**
     * 顺序查找算法
     *
     * @param arr    数组
     * @param number 查找的元素
     * @return 元素是否存在
     */
    public static boolean basicSearch(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return true;
            }
        }
        return false;
    }
}
