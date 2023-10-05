package com.example.linerlist.sequencelist;

import java.util.Iterator;

/*
    顺序表的实现
*/
public class SequenceList<T> implements Iterable<T>{

    // 存储元素的数组
    private T[] elements;
    // 记录当前顺序表中的元素个数
    private int N;

    // 构造方法
    public SequenceList(int capacity){
        // 初始化数组
        this.elements = (T[]) new Object[capacity];
        // 初始化长度
        this.N = 0;
    }

    // 将一个线性表置为空表
    public void clear(){
        this.N = 0;
    }

    // 判断当前线性表是否为空表
    public boolean isEmpty(){
        return N == 0;
    }

    // 获取线性表的长度
    public int length(){
        return N;
    }

    // 获取指定位置的元素
    public T get(int i){
        // 安全性校验
        if (i < 0 || i >= N)
            return null;
        return elements[i];
    }

    // 向线型表中添加元素t
    public void insert(T t){
        // 当前容量已不足，准备扩容，此处设定扩大 2 倍
        if (N == elements.length){
            resize(2 * elements.length);
        }
        elements[N++] = t;
    }

    // 在 i 索引处插入元素t
    public void insert(int i, T t){
        // 安全性校验
        if (i < 0 || i >= N)
            return;
        if (N == elements.length){
            resize(2 * elements.length);
        }
        // 先把i索引处的元素及其后面的元素依次向后移动一位
        for(int index = N; index > i; index--){
            elements[index] = elements[index-1];
        }
        // 再把t元素放到i索引处即可
        elements[i] = t;
        // 元素个数+1
        N ++;
    }

    // 删除指定位置i处的元素，并返回该元素
    public T remove(int i){
        // 安全性校验
        if (i < 0 || i >= N)
            return null;
        // 记录索引i处的值
        T current = elements[i];
        // 索引i后面元素依次向前移动一位即可
        for(int index = i; index < N - 1; index++){
            elements[index] = elements[index + 1];
        }
        // 元素个数-1
        N --;
        if (N < elements.length / 4){
            resize(elements.length / 2);
        }
        return current;
    }

    // 查找t元素第一次出现的位置
    public int indexOf(T t){
        for(int i = 0; i < N; i++){
            if (elements[i].equals(t)){
                return i;
            }
        }
        return -1;
    }

    // 根据参数newSize，重置 elements 的大小
    public void resize(int newSize){
        // 定义一个临时数组，指向原数组
        T[] temp = elements;
        // 创建新数组
        elements = (T[])new Object[newSize];
        //把原数组的数据拷贝到新数组即可
        for(int i = 0; i < N; i++){
            elements[i] = temp[i];
        }
    }


    /*
    重写iterator方法 实现顺序表的遍历
    */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator{
        private int cusor;
        public MyIterator(){
            this.cusor = 0;
        }
        @Override
        public boolean hasNext() {
            return cusor < N;
        }
        @Override
        public Object next() {
            return elements[cusor++];
        }
    }
}

