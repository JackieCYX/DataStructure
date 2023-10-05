package com.example.linerlist.linklist;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T>{

    /*
     结点类
    */
    private class Node {
        // 存储数据
        T item;
        // 下一个结点
        Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    // 记录头结点
    private Node head;
    // 记录链表的长度
    private int N;



    public LinkList() {
        // 初始化头结点 头结点不存放值, 只记录下一节点的地址
        this.head = new Node(null,null);
        // 初始化元素个数
        this.N = 0;
    }

    // 清空链表
    public void clear() {
        head.next = null;
        this.N = 0;
    }

    // 获取链表的长度
    public int length() {
        return N;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    // 获取指定位置 i 处 （i 从 0 开始）的元素
    public T get(int i) {
        // 安全性校验
        if (i < 0 || i >= N)
            return null;
        // 通过循环,从头结点开始往后找，依次找 i 次，就可以找到对应的元素
        Node node = head.next;
        for(int index = 0; index < i; index++){
            node = node.next;
        }
        return node.item;
    }

    // 向链表中添加元素t
    public void insert(T t) {
        // 找到尾结点
        Node node = head;
        while(node.next != null){
            node = node.next;
        }
        // 创建新结点，保存元素t
        Node newNode = new Node(t, null);
        // 让当前最后一个结点指向新结点
        node.next = newNode;
        // 元素的个数+1
        N ++;
    }

    // 向指定位置 i 处 （i 从 0 开始），添加元素t
    public void insert(int i, T t) {
        // 安全性校验
        if (i < 0 || i >= N)
            return;
        // 找到 i 位置前一个结点
        Node pre = head;
        for(int index = 0; index < i; index++){
            pre = pre.next;
        }
        // 找到 i 位置的结点
        Node curr = pre.next;
        // 创建新结点，并且新结点需要指向原来i位置的结点
        Node newNode = new Node(t, curr);
        // 原来 i 位置的前一个节点指向新结点即可
        pre.next = newNode;
        // 元素的个数+1
        N ++;
    }

    // 删除指定位置 i 处 （i 从 0 开始）的元素，并返回被删除的元素
    public T remove(int i) {
        // 安全性校验
        if (i < 0 || i >= N)
            return null;
        // 找到i位置的前一个节点
        Node pre = head;
        for(int index = 0; index < i; index++){
            pre = pre.next;
        }
        // i 位置的结点
        Node curr = pre.next;
        // 找到i位置的下一个结点
        // i位置的前一个结点指向 i位置的下一个结点
        pre.next = curr.next;
        // 元素个数-1
        N--;
        return curr.item;
    }

    // 查找元素t在链表中第一次出现的位置
    public int indexOf(T t) {
        if (t == null)
            return -1;
        // 从头结点开始，依次找到每一个结点，取出item，和t比较，如果相同，就找到了
        Node node = head;
        for(int i = 0; node.next != null; i++){
            node = node.next;
            if (t.equals(node.item)){
                return i;
            }
        }
        return -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator{
        private Node node;
        public MyIterator(){
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public Object next() {
            node = node.next;
            return node.item;
        }
    }
}

