package com.hnu;


import java.util.LinkedList;
import java.util.List;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
public class 二叉树中序遍历 {


    public static List<Integer> Traversal(TreeNode root, List<Integer> it) {

        if (root == null) return it;

        Traversal(root.left, it);
        it.add(root.val);
        Traversal(root.right, it);
        return it;
    }


    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> it = new LinkedList<>();
        return Traversal(root, it);

    }
}