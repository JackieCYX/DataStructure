package com.example.linearlist.linklist;

import java.util.Iterator;
public class LinkList<T> implements Iterable<T> {

    /**
     * 结点类
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
    private int length;

    public LinkList() {
        this.head = new Node(null, null);
        this.length = 0;
    }

    /**
     * 清空链表
     */
    public void clear() {
        head.next = null;
        this.length = 0;
    }

    /**
     * 获取链表的长度
     *
     * @return 当前链表长度
     */
    public int length() {
        return length;
    }

    /**
     * 判断链表是否为空
     *
     * @return true空，false非空
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * 获取指定位置 index 处 （i 从 0 开始）的元素
     *
     * @param index 指定索引
     * @return 返回指定索引的元素
     */
    public T get(int index) {
        if (index < 0 || index >= length)
            return null;
        Node node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    /**
     * 向链表中添加元素t
     *
     * @param t
     */
    public void insert(T t) {
        // 找到尾结点
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        // 创建新结点，保存元素t
        Node newNode = new Node(t, null);
        node.next = newNode;
        length++;
    }

    /**
     * 向指定位置Index处，添加元素t
     *
     * @param index
     * @param t
     */
    public void insert(int index, T t) {
        if (index < 0 || index >= length)
            return;
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node curr = pre.next;
        Node newNode = new Node(t, curr);
        pre.next = newNode;
        length++;
    }

    /**
     * 删除指定位置Index的元素，并返回被删除的元素
     *
     * @param index
     * @return
     */
    public T remove(int index) {
        if (index < 0 || index >= length)
            return null;
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node curr = pre.next;
        pre.next = curr.next;
        length--;
        return curr.item;
    }

    /**
     * 查找元素t在链表中第一次出现的位置
     *
     * @param t
     * @return
     */
    public int indexOf(T t) {
        if (t == null)
            return -1;
        Node node = head;
        for (int i = 0; node.next != null; i++) {
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

    private class MyIterator implements Iterator {
        private Node node;

        public MyIterator() {
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
