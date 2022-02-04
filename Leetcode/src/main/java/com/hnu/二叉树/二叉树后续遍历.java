package com.hnu.二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class 二叉树后续遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> S1=new Stack<>();
        LinkedList<Integer> ls=new LinkedList<Integer>();
        if(root==null) return ls;
        S1.push(root);
        while(!S1.isEmpty()){ //当栈S1不为空时：1.先S1出栈 2.将从S1出栈的栈顶元素入栈S2
            TreeNode P=S1.pop();
            ls.addFirst(P.val);//头插法，对于一个三个节点的子树，父节点是最后被访问的。放入结果的末尾
            if(P.left!=null) S1.push(P.left);//左子树被后访问应该先入栈
            if(P.right!=null) S1.push(P.right);//右子树被后先访问，访问后入栈
        }
        return ls;
    }
}