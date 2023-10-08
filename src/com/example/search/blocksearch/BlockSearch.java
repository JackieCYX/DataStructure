package com.example.search.blocksearch;

/**
 * 分块查找实现类
 */
public class BlockSearch {
    int[] arr; // 存放原始数组
    IndexNode[] index; // 存放索引表
    int blockSize; // 存放每个子块的大小

    public BlockSearch(int[] arr, int blockSize) {
        this.arr = arr;
        this.blockSize = blockSize;
        this.index = createIndex(arr, blockSize);
    }

    public IndexNode[] createIndex(int[] arr, int blockSize) {
        int len = arr.length; // 数组长度
        int num = len % blockSize == 0 ? len / blockSize : len / blockSize + 1; // 计算需要划分多少个子块
        IndexNode[] index = new IndexNode[num];
        for (int i = 0; i < num; i++) {
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize - 1, len - 1); // 计算每个子块在数组中的结束位置
            int maxKey = arr[start];
            for (int j = start + 1; j <= end; j++) {
                if (arr[j] > maxKey) {
                    maxKey = arr[j];
                }
            }
            index[i] = new IndexNode(maxKey, start, end);
        }
        return index;
    }

    public int search(int value) {
        int i = binarySearch(index, value);
        if (i != -1) {
            int start = index[i].start;
            int end = index[i].end;
            for (int j = start; j <= end; j++) {
                if (arr[j] == value) {
                    return j;
                }
            }
        }
        return -1;
    }

    public int binarySearch(IndexNode[] index, int value) {
        int left = 0;
        int right = index.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (value <= index[mid].maxKey) { //如果待查元素小于等于中间结点的最大值，说明在左边或者就是中间结点
                if (mid == 0 || value > index[mid - 1].maxKey) { //如果中间结点是第一个结点，或者待查元素大于前一个结点的最大值，说明就是中间结点
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
