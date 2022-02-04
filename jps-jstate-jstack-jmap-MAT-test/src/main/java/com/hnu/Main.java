package com.hnu;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {







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




    public List<List<Integer>> levelOrder(TreeNode root) {


    List<List<Integer>> list=new LinkedList<List<Integer>>();//存放结果
        Stack<TreeNode> stack= new Stack<TreeNode>();

        if(null==root) return list;
        stack.push(root);

      //  if()
        /*

             Input: root = [3,9,20,null,null,15,7]
              Output: [[3],[9,20],[15,7]]


         */



        while(!stack.isEmpty())
        {
            TreeNode node = stack.pop();
            List<Integer> l=new LinkedList<Integer>();
            /*TreeNode tem =node.right;
          node.right =node.left;
            node.left=tem;*/
            l.add(node.val);
            if(node.right!=null)  stack.push(node.right);
            if(node.left!=null)  stack.push(node.left);
        }





    }

}
