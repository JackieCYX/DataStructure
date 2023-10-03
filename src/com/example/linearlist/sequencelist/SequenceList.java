package com.example.linearlist.sequencelist;

import java.util.Arrays;
import java.util.Iterator;

public class SequenceList<T> implements Iterable<T> {

    public T[] array;
    public int usedSize;

    public SequenceList(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.usedSize = 0;
    }

    /**
     * 将一个线性表置为空表
     */
    public void clear() {
        this.usedSize = 0;
    }

    /**
     * 判断当前线性表是否为空表
     *
     * @return true空，false非空
     */
    public boolean isEmpty() {
        return usedSize == 0;
    }

    /**
     * 获取顺序表的长度
     *
     * @return 顺序表的长度
     */
    public int size() {
        return usedSize;
    }

    /**
     * 查找顺序表对应下标的元素值
     *
     * @param pos 指定位序
     * @return 对应下标的元素值
     */
    public T getPos(int pos) {
        if (pos < 0 || pos >= usedSize) {
            return null;
        }
        return array[pos];
    }

    /**
     * 向线性表添加元素
     *
     * @param t 插入元素
     */
    public void insert(T t) {
        if (isFull()) {
            resize(2 * array.length);
        }
        array[usedSize++] = t;
    }

    /**
     * 在指定位置插入数据
     *
     * @param pos 位序
     * @param t   插入元素
     */
    public void add(int pos, T t) {
        if (pos < 0 || pos >= usedSize) {
            return;
        }
        if (isFull()) {
            // 判单是否已满，满了则扩容
            resize(2 * array.length);
        }
        for (int index = usedSize; index > pos; index--) {
            this.array[index] = this.array[index - 1];
        }
        array[pos] = t;
        usedSize++;
    }

    /**
     * 删除指定位置i处的元素，并返回该元素
     *
     * @param index 位序
     */
    public T remove(int index) {
        if (index < 0 || index >= usedSize)
            return null;
        T current = array[index];
        for (int i = index; i < usedSize - 1; i++) {
            array[i] = array[i + 1];
        }
        this.usedSize--;
        if (usedSize < array.length / 4) {
            resize(array.length / 2);
        }
        return current;
    }

    /**
     * 查找元素第一次出现的位置
     *
     * @param t 查询的元素
     * @return 下标值
     */
    public int search(T t) {
        for (int i = 0; i < usedSize; i++) {
            if (array[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断顺序表是否已满
     *
     * @return true已满，false未满
     */
    public boolean isFull() {
        return array.length == usedSize;
    }

    /**
     * 充值array数组的容量
     *
     * @param newSize 新的数组容量大小
     */
    public void resize(int newSize) {
        // 定义一个临时数组，指向原数组
        T[] temp = array;
        // 创建新数组
        array = (T[]) new Object[newSize];
        // 将原数组的数据拷贝到新数组
        if (usedSize >= 0) System.arraycopy(temp, 0, array, 0, usedSize);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        private int cursor;

        public MyIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < usedSize;
        }

        @Override
        public Object next() {
            return array[cursor++];
        }
    }
}
