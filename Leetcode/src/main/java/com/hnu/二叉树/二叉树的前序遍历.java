package com.hnu.二叉树;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;



/*

二叉树遍历的顺序其实可以理解为操作节点的顺序。
因为树的访问总是从根节点开始的。
对于前序遍历：
对于任意一个当前节点，始终遵循当前节点先出栈，然后左子树或者左叶子节点出栈，然后再右子树，右节点出栈。
二叉树的深度优先遍历：前中后
广度优先遍历：层次遍历。

多叉树没有中序遍历。很多问题可以归结为树的遍历问题。


二叉树节点访问顺序和和处理节点的顺序。
对于前序，节点的处理顺序与访问顺序是一致。
而中序则不然。
三部曲：



 */

class 二叉树的前序遍历{


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





}