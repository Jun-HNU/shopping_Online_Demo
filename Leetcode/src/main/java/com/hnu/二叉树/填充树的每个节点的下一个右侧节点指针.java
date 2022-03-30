package com.hnu.二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class 填充树的每个节点的下一个右侧节点指针 {
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
    public Node connect(Node root) {


        if(root==null) return root;

        Queue<Node> q= new LinkedList<>();

        q.add(root);

        while(!q.isEmpty())
        {
            int currentSize = q.size();
            Node lastNode=null;//当遍历树的当前层时，让这个变量记录下遍历过的上一个节点，
            // 以便于将上一个节点与当前节点进建立联系。
            //每一层节点的个数为currentSize
            for (int i = 0; i <currentSize ; i++) {

                Node node = q.poll();
                node.next=null;//首先将当前节点的下一个节点置为空
                if(lastNode!=null)
                    lastNode.next=node;//如果当前层的上一个节点不为空，
                // 将当前节点作为上一个节点的next节点
                lastNode=node;//将当前层的上一个节点与当前节点建立联系。
                if (node.left!=null)
                    q.add(node.left);
                if (node.right!=null)
                    q.add(node.right);
            }

        }

        return root;
    }
};
