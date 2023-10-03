package com.example.linearlist.doublelinklist;

import com.example.linearlist.linklist.LinkList;

import java.util.Iterator;

public class DoubleLinkList<T> implements Iterable<T> {

    private Node head;
    private Node last;
    private int length;

    private class Node {
        public T item;
        public Node next;
        public Node pre;

        public Node(T item, Node next, Node pre) {
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
    }

    public DoubleLinkList() {
        this.head = new Node(null, null, null);
        this.last = null;
        this.length = 0;
    }

    public void clear() {
        this.head.next = null;
        this.head.pre = null;
        this.head.item = null;
        this.last = null;
        this.length = 0;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.next.item;
    }

    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    public void insert(T t) {
        if (isEmpty()) {
            Node newNode = new Node(t, head, null);
            last = newNode;
            head.next = last;
        } else {
            Node newNode = new Node(t, last, null);
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    public void insert(int index, T t) {
        if (index < 0 || index >= length)
            return;
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node curr = pre.next;
        Node newNode = new Node(t, pre, curr);
        pre.next = newNode;
        curr.pre = newNode;
        length++;
    }

    public T get(int index) {
        if (index < 0 || index >= length)
            return null;
        Node node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public int indexOf(T t) {
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

    public T remove(int i){
        // 安全性校验
        if (i < 0 || i >= length)
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
        length --;
        return curr.item;
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
