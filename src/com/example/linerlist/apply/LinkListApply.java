package com.example.linerlist.apply;

import com.example.linerlist.linklist.LinkList;

import java.util.Iterator;

public class LinkListApply<T> implements Iterable<T> {

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


    public LinkListApply() {
        // 初始化头结点 头结点不存放值, 只记录下一节点的地址
        this.head = new Node(null, null);
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
        for (int index = 0; index < i; index++) {
            node = node.next;
        }
        return node.item;
    }

    // 向链表中添加元素t
    public void insert(T t) {
        // 找到尾结点
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        // 创建新结点，保存元素t
        Node newNode = new Node(t, null);
        // 让当前最后一个结点指向新结点
        node.next = newNode;
        // 元素的个数+1
        N++;
    }

    // 向指定位置 i 处 （i 从 0 开始），添加元素t
    public void insert(int i, T t) {
        // 安全性校验
        if (i < 0 || i >= N)
            return;
        // 找到 i 位置前一个结点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        // 找到 i 位置的结点
        Node curr = pre.next;
        // 创建新结点，并且新结点需要指向原来i位置的结点
        Node newNode = new Node(t, curr);
        // 原来 i 位置的前一个节点指向新结点即可
        pre.next = newNode;
        // 元素的个数+1
        N++;
    }

    // 删除指定位置 i 处 （i 从 0 开始）的元素，并返回被删除的元素
    public T remove(int i) {
        // 安全性校验
        if (i < 0 || i >= N)
            return null;
        // 找到i位置的前一个节点
        Node pre = head;
        for (int index = 0; index < i; index++) {
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
        for (int i = 0; node.next != null; i++) {
            node = node.next;
            if (t.equals(node.item)) {
                return i;
            }
        }
        return -1;
    }

    // ======================================================
    // 单链表难点问题 => 链表反转

    // 方案1：通过栈实现（重点）
    // 1. 先让链表所有结点入栈
    // 2. 依次出栈，再使用尾插法重新插入
    // 空间时间复杂度O(n)
    public void reverse_1() {
        LinkListApply<Node> list = new LinkListApply<>();
        Node cur = head.next;
        // 链表所有结点入栈
        while (cur != null) {
            list.insert(cur);
            cur = cur.next;
        }
        // 使用尾插法重新插入
        Node tail = head;
        int index = list.length() - 1;
        while (index >= 0) {
            Node pop = list.get(index--);
            tail.next = pop;
            tail = pop;
            tail.next = null;
        }
    }

    // 方案2：头插法（重点）
    public void reverse_2() {

        Node cur = head.next;
        head.next = null;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = head.next;
            head.next = cur;
            cur = temp;
        }
    }

    // 方案3：三指针法（难，性能最佳）
    public void reverse_3() {
        Node pre = null;
        Node cur = null;
        Node next = null;
        cur = head.next;
        while (cur!=null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = pre;
    }

    // 方案4：递归（最难理解）
    public void reverse() {
        if (isEmpty()) {
            return;
        }
        reverse_4(head.next);
    }
    // 反转指定结点curr，并把反转后的结点返回
    public Node reverse_4(Node curr) {
        if (curr.next == null) {
            head.next = curr;
            return curr;
        }
        // 递归的反转当前结点curr的下一个结点；返回值就是链表反转后，当前结点的上一个结点
        Node pre = reverse_4(curr.next);
        // 让返回的结点的下一个结点变为当前结点curr；
        pre.next = curr;
        // 把当前结点的下一个结点变为null
        curr.next = null;
        return curr;

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

