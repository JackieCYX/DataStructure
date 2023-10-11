package com.example.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private static class TreeNode {
        private char val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }
    }
    private TreeNode root;

    /**
     * 先序遍历（递归）
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历（递归）
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    /**
     * 后序遍历（递归）
     * @param root
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    /**
     * 层次遍历（递归）
     * @param root
     */
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
