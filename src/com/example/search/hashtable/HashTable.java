package com.example.search.hashtable;

public class HashTable {
    int size; // 存放哈希表的大小
    HashNode[] table; // 存放哈希表数组

    public HashTable(int size) {
        this.size = size;
        this.table = new HashNode[size];
    }

    // 定义哈希函数
    public int hash(int key) {
        return key % size;
    }

    public void insert(int key, int value) {
        int index = hash(key);
        HashNode node = new HashNode(key, value);
        if (table[index] == null) { //如果该地址为空，直接插入
            table[index] = node;
        } else { //如果该地址不为空，说明发生了冲突，采用链地址法解决冲突
            HashNode temp = table[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    public int search(int key) {
        int index = hash(key);
        if (table[index] == null) {
            return -1;
        } else {
            HashNode temp = table[index];
            while (temp != null) {
                if (temp.key == key) {
                    return temp.value;
                }
                temp = temp.next;
            }
            return -1;
        }
    }
}
