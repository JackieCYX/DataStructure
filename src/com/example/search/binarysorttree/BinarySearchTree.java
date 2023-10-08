package com.example.search.binarysorttree;

public class BinarySearchTree {
    public static void main(String[] args) {
        int[] array = {42, 17, 56, 89, 23, 5, 74, 33, 61, 10, 29, 92, 70, 84, 68, 11, 39, 95, 77, 6};
        TreeNode rootNode = null;
        // 构造一棵二叉搜索树
        for (int i = 0; i < array.length; i++) {
            rootNode = TreeNode.insert(rootNode, array[i]);
        }
        // 中序遍历二叉搜索树
        TreeNode.inOrder(rootNode);
        // 查找指定值
        TreeNode findNode = TreeNode.find(rootNode, 33);
        // 删除指定值
        TreeNode.remove(rootNode, 61);

    }

}
