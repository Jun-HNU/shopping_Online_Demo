package com.hnu.二叉树;


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




class Solution {
    public void fun(TreeNode root, List<Integer> list)
{
if (root==null) return;
list.add(root.val);
fun(root.left,list);
fun(root.right,list); 

}
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        
fun(root,list);

return list;
    }




    public List<Integer> preorderTraversal2(TreeNode root) {

        List<Integer> list = new LinkedList<Integer>();

        Stack<TreeNode> stack=new Stack<TreeNode>();
        TreeNode p=root;
        if(p!=null)
            stack.push(p);
        while(!stack.empty()){
            TreeNode node=stack.pop();
            list.add(node.val);
            if(null!=node.right)
            {
                stack.push(node.right);
            }
            if(null!=node.left)
            {
                stack.push(node.left);
            }
        }
        return list;

    }
}