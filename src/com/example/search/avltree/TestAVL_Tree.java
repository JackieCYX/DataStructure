package com.example.search.avltree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 平衡二叉树
 */
public class TestAVL_Tree {

    /**
     * 存放数据的节点类
     */
    static class TreeNode {
        private int bf = 0; // BalanceFactor（平衡因子）
        private int data;
        private TreeNode parent_node; // 该节点的父节点
        private TreeNode left_child, right_child; // 该节点的左右子节点
        private int count = 0; // 记录该条数据被重复插入的次数

        public TreeNode(int data) {
            this.data = data;
        }

        /**
         * 此处注意不能直接打印左右子节点和父节点的全部节点信息
         * 因为节点之间有互相引用关系，如果全部打印会导致堆栈溢出
         * 故此处值打印父节点和左右子节点的值
         *
         * @return
         */
        @Override
        public String toString() {
            String p_data = null, l_data = null, r_data = null;
            if (parent_node != null) {
                p_data = String.valueOf(parent_node.data);
            }
            if (left_child != null) {
                l_data = String.valueOf(left_child.data);
            }
            if (right_child != null) {
                r_data = String.valueOf(right_child.data);
            }
            return "TreeNode{" +
                    "bf=" + bf +
                    ", data=" + data +
                    ", parent_node=" + parent_node +
                    ", left_child=" + left_child +
                    ", right_child=" + right_child +
                    ", count=" + count +
                    '}';
        }

        /**
         * 因为平衡二叉树的节点值唯一，所以此处只需要比较值相等
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return bf == treeNode.bf && data == treeNode.data && count == treeNode.count && Objects.equals(parent_node, treeNode.parent_node) && Objects.equals(left_child, treeNode.left_child) && Objects.equals(right_child, treeNode.right_child);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bf, data, parent_node, left_child, right_child, count);
        }

    }

    /**
     * 平衡二叉树
     * 条件:
     * 1.任意节点左子树上的节点值小于该节点的值,右子树上的节点值大于该节点的值,该节点的子节点也适用这个规律
     * 2.每个节点左右子树的深度差绝对值不大于1
     */
    static class AVLTree {
        private Queue<TestAVL_Tree.TreeNode> tree = new LinkedList<TestAVL_Tree.TreeNode>();

        /**
         * 计算新插入节点的位置
         *
         * @param old_node 旧节点
         * @param new_node 新节点
         */
        private void indexOf(TreeNode old_node, TreeNode new_node) {
            if (new_node.data == old_node.data) { //新节点的值等于旧节点的值
                old_node.count++; // 不计算新节点的下标，直接在旧节点的count+1
            } else if (new_node.data < old_node.data) {
                if (old_node.left_child == null) { //如果左子树为空,就放入旧节点的左子树
                    old_node.left_child = new_node;
                    new_node.parent_node = old_node;
                } else {
                    indexOf(old_node.left_child, new_node); //如果左子树不为空,则递归计算位置
                }
            } else { //新节点的值大于等于旧节点的值
                if (old_node.right_child == null) { //如果右子树为空,就放入旧节点的右子树
                    old_node.right_child = new_node;
                    new_node.parent_node = old_node;
                } else {
                    indexOf(old_node.right_child, new_node); //如果右子树不为空,则递归计算位置
                }
            }
        }

        /**
         * 插入节点
         *
         * @param data 节点值
         */
        public void insertNode(int data) {
            TreeNode new_node = new TreeNode(data);
            int size = tree.size();
            if (size == 0) {//如果树里没有节点,是第一次添加
                tree.add(new_node);
            } else {
                indexOf(getRootNode(), new_node);//计算插入的位置
                if (new_node.parent_node != null) {
                    tree.add(new_node);//添加到队列
                }
                reCountNodeBF();//重新计算每个节点的平衡因子
                TreeNode minUnBalanceTree = countMinUnBalanceNode(new_node);//计算最小不平衡子树
                if (minUnBalanceTree != null) {//代表失衡
                    System.out.println("最小不平衡子树为： " + minUnBalanceTree.toString());
                    executeRotation(new_node, minUnBalanceTree);//执行旋转
                }
            }
        }

        /**
         * 计算最小不平衡子树
         * 可能存在的情况:
         * 1.该节点是根节点(没有父节点)
         * 2.该节点的父节点平衡因子>1,代表此节点是最小不平衡子树
         * 3.该节点的父节点平衡因子<1,递归继续向上查找
         *
         * @param node 新插入的节点
         * @return 最小不平衡子树
         */
        private TreeNode countMinUnBalanceNode(TreeNode node) {
            TreeNode from_node = node.parent_node;
            if (from_node != null) { //此节点不是根节点
                int bf_abs = Math.abs(from_node.bf);
                if (bf_abs > 1) { //平衡因子绝对值>1代表此节点失衡,并且是最小不平衡子树
                    return from_node;
                } else {
                    return countMinUnBalanceNode(from_node); // 否则递归调用向上查找
                }
            } else { //此节点是根节点 只需要判断根节点的不平衡因子,不需要往上递归
                int bf_abs = Math.abs(node.bf);
                if (bf_abs > 1) {//平衡因子绝对值>1代表此节点失衡,并且是最小不平衡子数
                    return node;
                }
            }
            return null;
        }

        /**
         * 节点右旋
         *
         * @param node 需要右旋的节点
         */
        private void rightRotate(TreeNode node) {
            TreeNode left_child = node.left_child;//当前节点的左子树
            TreeNode parent_node = node.parent_node;//当前节点的父节点
            if (parent_node != null) {//如果当前节点不是根节点
                //判断当前节点是父节点的左子树还是右子树
                if (node.equals(parent_node.left_child)) {
                    node.parent_node.left_child = left_child;//用左子树替换掉当前节点在父节点的位置
                } else if (node.equals(parent_node.right_child)) {
                    node.parent_node.right_child = left_child;//用左子树替换掉当前节点在父节点的位置
                }
            }
            left_child.parent_node = parent_node;//原左子树(现父节点)的父节点改为原父节点(现右子树)的父节点

            TreeNode left_child_right = left_child.right_child;//获取原左子树的右节点
            //如果不为空则把左子树的右节点设为原父节点的左子树
            if (left_child_right != null) {
                left_child_right.parent_node = node;
            }
            node.left_child = left_child_right;//原左子树右节点的父节点改为现右子树(原父节点)的左节点

            left_child.right_child = node;//现父节点(原左子节点)的右子树改为原父节点
            node.parent_node = left_child;//现右子树(原父节点)的父节点改为原左子树(现父节点)
            reCountNodeBF();//右旋完成重新计算节点的平衡因子
            System.out.println("右旋后: " + tree.toString());
        }

        /**
         * 节点左旋
         *
         * @param node 需要左旋的节点
         */
        private void leftRotate(TreeNode node) {
            TreeNode right_child = node.right_child;
            TreeNode parent_node = node.parent_node;
            if (parent_node != null) { //如果当前节点不是根节点
                //判断当前节点是父节点的左子树还是右子树
                if (node.equals(parent_node.left_child)) {
                    parent_node.left_child = right_child;
                } else if (node.equals(parent_node.right_child)) {
                    parent_node.right_child = right_child;
                }
            }
            right_child.parent_node = parent_node; //原右子树(现父节点)的父节点改为原父节点(现左子树)的父节点

            TreeNode right_child_left = right_child.left_child;
            //如果不为空则把右子树左节点的父节点设为原父节点(现左子树的)的右节点
            if (right_child_left != null) {
                right_child_left.parent_node = node;
            }
            node.right_child = right_child_left;//原右子树左节点的父节点改为现左子树(原父节点)的右节点

            right_child.left_child = node;//现父节点(原右子树)的左子树改为原父节点
            node.parent_node = right_child;//现左子树(原父节点)的父节点改为原右子树(现父节点)
            reCountNodeBF();//左旋完成重新计算节点的平衡因子
            System.out.println("左旋后: " + tree.toString());
        }

        /**
         * 执行旋转
         * 可能出现4中情况:
         * 1.左左 (直接右旋)
         * 2.左右(先左旋变为左左再右旋)
         * 3.右右(直接左旋)
         * 4.右左(先右旋变为右右再左旋)
         *
         * @param new_node         新插入的节点
         * @param minUnBalanceTree 最小不平衡子树
         */
        private void executeRotation(TreeNode new_node, TreeNode minUnBalanceTree) {
            System.out.println("旋转前：" + tree.toString());
            TreeNode old_node = new_node.parent_node;
            if (minUnBalanceTree.bf > 1) { //左边多，需要右旋
                if (new_node != old_node.left_child) { //左右(当前节点不是旧节点的左子树)
                    leftRotate(old_node);
                    minUnBalanceTree = countMinUnBalanceNode(new_node);
                }
                rightRotate(minUnBalanceTree);
            } else if (minUnBalanceTree.bf < -1) { //右边多,需要左旋
                if (new_node != old_node.right_child) {//右左(当前节点不是旧节点的右子树)
                    rightRotate(old_node);//先右旋变为右右
                    minUnBalanceTree = countMinUnBalanceNode(new_node);//计算最小不平衡子树
                }
                leftRotate(minUnBalanceTree);//左旋
            }
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insertNode(7);
        avlTree.insertNode(6);
        System.out.println("最终结果：" + avlTree.tree.toString());
    }
}
