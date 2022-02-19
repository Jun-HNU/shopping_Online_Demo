package com.hnu.二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class 二叉树的最小深度 {

   void  help(TreeNode root , List<Integer> ls)
    {

ls.add(root.val);
        if(root.left!=null)
            help(root.left,ls);
        if(root.right!=null)
            help(root.right,ls);

    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ls= new LinkedList<>();
        if(root!=null)
        {
            help(root,ls);
        }
          return ls;

    }


    public List<Integer> preorderTraversal2(TreeNode root) {

        List<Integer> ls= new LinkedList<>();
        Stack<TreeNode> s= new Stack<>();

        if(root!=null)
        {
         s.push(root);
        }
        while(!s.isEmpty())
        {
            TreeNode p =s.pop();
            ls.add(p.val);
            if(p.right!=null)
                s.push(p.right);
            if(p.left!=null)
                s.push(p.left);

        }
        return  ls;
    }
}
