package com.example.search.binarysorttree;

public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public static TreeNode find(TreeNode rootNode, int value) {
        TreeNode cur = rootNode;
        while (cur != null) {
            if (cur.value < value) {
                cur = cur.right;
            } else if (cur.value > value) {
                cur = cur.left;
            } else {
                return cur;
            }
        }
        return null;
    }

    public static TreeNode insert(TreeNode rootNode, int value) {
        if (rootNode == null) {
            rootNode = new TreeNode(value);
            return rootNode;
        }
        TreeNode pre = null;
        TreeNode cur = rootNode;

        while (cur != null) {
            pre = cur;
            if (cur.value < value) {
                cur = cur.right;
            } else if (cur.value > value) {
                cur = cur.left;
            } else {
                return rootNode;
            }
        }
        if (value < pre.value) {
            pre.left = new TreeNode(value);
        } else {
            pre.right = new TreeNode(value);
        }
        return rootNode;
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public static void remove(TreeNode rootNode, int value) {
        TreeNode cur = rootNode;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.value == value) {
                removeNode(rootNode, pre, cur);
                return;
            } else if (cur.value < value) {
                pre = cur;
                cur = cur.right;
            } else {
                pre = cur;
                cur = cur.left;
            }
        }
    }

    private static void removeNode(TreeNode rootNode, TreeNode pre, TreeNode cur) {
        if (cur.left == null) { // 左子树为空
            if (cur == rootNode) { // 删除父节点
                rootNode = cur.right;
            } else if (pre.left == cur) { // 删除父节点左侧节点
                pre.left = cur.right;
            } else { // 删除父节点右侧节点
                pre.right = cur.right;
            }
        } else if (cur.right == null) { // 右子树为空
            if (cur == rootNode) {
                rootNode = cur.left;
            } else if (pre.left == cur) {
                pre.left = cur.left;
            } else {
                pre.right = cur.left;
            }
        } else { // 左右子树都不为空，直接前驱或者直接后继顶替（难点在于如何找到）
            TreeNode targetPre = cur;
            TreeNode target = cur.right;
            while (target.left != null) {
                targetPre = target;
                target = target.left;
            }
            cur.value = target.value;
            target.left = target.right;
        }
    }
}
