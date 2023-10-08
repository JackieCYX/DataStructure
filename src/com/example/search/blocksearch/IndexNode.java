package com.example.search.blocksearch;

/**
 * 索引表结点
 */
public class IndexNode {
    int maxKey; // 存放每个子块中最大元素的值
    int start; // 存放每个子块在数组中的起始位置
    int end; // 存放每个子块在数组中的结束位置

    public IndexNode(int maxKey, int start, int end) {
        this.maxKey = maxKey;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "IndexNode{" +
                "maxKey=" + maxKey +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
