package com.hnu;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

    public static List<Integer> inorderTraversal2(TreeNode root) {


        Stack<TreeNode> stack=new Stack<>();//这里的栈就相当于一个缓存。
        LinkedList<Integer> lis=new LinkedList<>();
        TreeNode cur=root;
        while (null!=cur||!stack.isEmpty())
        {
            if(null!=cur)
            {
            stack.push(cur);
            cur=cur.left;
            }
            else
            {
                TreeNode pop = stack.pop();
                lis.add(pop.val);
                cur=pop.right;
            }

        }



        return lis;
    }


    public static List<Integer> Traversal(TreeNode root, List<Integer> it) {

        if (root == null) return it;
        //将list对象的引用通过，return返回，这样，这个引用对于所有的迭代的当前函数栈都是可见的。
        //函数栈，就是一个栈，先进后出的原则。

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