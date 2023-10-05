package com.example.linerlist.doublelinklist;

import java.util.Iterator;

public class DoubleLinkList<T> implements Iterable<T> {

    // 首结点
    private Node head;
    // 尾结点
    private Node last;
    // 链表的长度
    private int N;

    // 结点类
    private class Node{
        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        // 存储数据
        public T item;
        // 指向上一个结点
        public Node pre;
        // 指向下一个结点
        public Node next;
    }

    public DoubleLinkList() {
        // 初始化头结点和尾结点
        this.head = new Node(null,null,null);
        this.last = null;
        // 初始化元素个数
        this.N = 0;
    }

    // 清空链表
    public void clear(){
        this.head.next = null;
        this.head.pre = null;
        this.head.item = null;
        this.last = null;
        this.N = 0;
    }

    // 获取链表长度
    public int length(){
        return N;
    }

    // 判断链表是否为空
    public boolean isEmpty(){
        return N == 0;
    }

    // 获取第一个元素
    public T getFirst(){
        if (isEmpty())
            return null;
        return head.next.item;
    }

    // 获取最后一个元素
    public T getLast(){
        if (isEmpty())
            return null;
        return last.item;
    }

    // 插入元素t
    public void insert(T t){
        if (isEmpty()){
            // 如果链表为空：
            // 创建新的结点
            Node newNode = new Node(t, head, null);
            // 让新节点赋值尾结点
            last = newNode;
            // 让头结点指向尾结点
            head.next = last;
        }else {
            // 如果链表不为空
            // 创建新的结点
            Node newNode = new Node(t, last, null);
            // 尾结点指向新结点
            last.next = newNode;
            // 让新节点赋值尾结点
            last = newNode;
        }
        // 元素个数+1
        N ++;
    }

    // 向指定位置 i 处 （i 从 0 开始），添加元素t
    public void insert(int i,T t){
        // 安全性校验
        if (i < 0 || i >= N)
            return;
        // 找到 i 位置的前一个结点
        Node pre = head;
        for(int index = 0; index < i; index++){
            pre = pre.next;
        }
        // 找到 i 位置的结点
        Node curr = pre.next;
        // 创建新结点
        Node newNode = new Node(t, pre, curr);
        // 让 i 位置的前一个结点指向新结点
        pre.next = newNode;
        // 让 i 位置的下一个结点指向新节点
        curr.pre = newNode;
        // 元素个数+1
        N ++;
    }

    // 获取指定位置 i 处 （i 从 0 开始）的元素
    public T get(int i){
        // 安全性校验
        if (i < 0 || i >= N)
            return null;
        Node node = head.next;
        for(int index = 0; index < i; index++){
            node = node.next;
        }
        return node.item;
    }

    // 找到元素t在链表中第一次出现的位置
    public int indexOf(T t){
        if (t == null)
            return -1;
        Node node = head;
        for(int i = 0; node.next != null; i++){
            node = node.next;
            if (t.equals(node.item)){
                return i;
            }
        }
        return -1;
    }

    // 删除指定位置 i 处 （i 从 0 开始）的元素，并返回被删除的元素
    public T remove(int i){
        // 安全性校验
        if (i < 0 || i >= N)
            return null;
        // 找到i位置的前一个结点
        Node pre = head;
        for(int index = 0; index < i; index++){
            pre = pre.next;
        }
        // 找到 i 位置的结点
        Node curr = pre.next;
        // 让 i 位置的前一个结点指向 i 位置的下一个结点
        pre.next = curr.next;
        // 让 i 位置的下一个结点指向 i 位置的前一个结点
        curr.next.pre = pre;
        // 元素的个数-1
        N --;
        return curr.item;
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

