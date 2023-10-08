package com.example.search.hashtable;

/**
 * 定义哈希表结点类
 */
public class HashNode {
    int key; // 存放元素值
    int value; // 存放元素在数组中的下标
    HashNode next; // 指向下一个结点
    public HashNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "HashNode{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
