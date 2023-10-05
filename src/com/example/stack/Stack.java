package com.example.stack;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    // 记录首结点
    private Node head;
    // 栈中元素德个数
    private int N;

    private class Node {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Stack() {
        this.head = new Node(null, null);
        this.N = 0;
    }

    /**
     * 判断当前栈中元素个数是否为 0
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取栈中元素的个数
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 把 t 元素压入栈，使用头插法
     *
     * @param t
     */
    public void push(T t) {
        Node newNode = new Node(t, null);
        // 让新结点指向原来的第一个结点
        newNode.next = head.next;
        head.next = newNode;
        N++;
    }

    /**
     * 弹出栈顶元素
     *
     * @return
     */
    public T pop() {
        Node oldFirst = head.next;
        if (oldFirst == null) {
            return null;
        }
        head.next = oldFirst.next;
        N--;
        return oldFirst.item;
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
