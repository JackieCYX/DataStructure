package com.example.search.binarysorttree;

public class BinarySortTree {
    private Node rootNode;

    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 2, 8};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < array.length; i++) {
            binarySortTree.addNode(new Node(array[i]));
        }

        binarySortTree.postOrder();
    }

    private void addNode(Node node) {
        if (rootNode==null){
            rootNode=node;
        }else{
            rootNode.addNode(node);
        }
    }
    private void postOrder() {
        if (rootNode == null) {
            System.out.println("这是一棵空树");
        } else {
            rootNode.postOrder();
        }
    }
}
