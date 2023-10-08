package com.example.search.binarysorttree;

public class Node {
    private Node leftNode;
    private Node rightNode;
    private int value;

    public Node(int value) {
        super();
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public void addNode(Node node) {
        if (node == null) {
            System.out.println("该结点为空，不进行添加");
            return;
        }

        if (node.value < value) {
            if (leftNode == null) {
                leftNode = node;
            } else {
                leftNode.addNode(node);
            }
        } else {
            if (rightNode == null) {
                rightNode = node;
            } else
                rightNode.addNode(node);
        }
    }

    public void postOrder() {
        if (leftNode != null) {
            leftNode.postOrder();
        }
        if (rightNode != null) {
            rightNode.postOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
