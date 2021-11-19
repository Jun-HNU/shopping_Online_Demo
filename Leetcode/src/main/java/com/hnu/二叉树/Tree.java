package com.hnu.二叉树;

class Node{
    Node left;
    Node right;
    String key;
    int value;
    Node(String str)
    {
        this.key=str;

    }
}

public class Tree {

    public static void traverse(Node node){
if(node!=null) {

    traverse(node.left);
    System.out.println(node.key);
    traverse(node.right);
}
    }

    public static void main(String[] args) {
        Node a = new Node("a");
        Node b = new Node("b");

        Node c = new Node("c");
        a.left=b;
        a.right=c;
        traverse(a);

    }


}
