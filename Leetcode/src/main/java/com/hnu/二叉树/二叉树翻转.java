package com.hnu.二叉树;

public class 二叉树翻转 {
    /*
    从上到下遍历所有非叶子节点，将当前节点的左右子树交换。
     */

    void swap(TreeNode node)
    {
        // System.out.println(node.left.val);
        TreeNode tmp=node.left;
        node.left=node.right;
        node.right=tmp;
        //System.out.println(node.left.val);
    }
    void help(TreeNode node){
        if(node==null) return;
        if(node.left!=null||node.right!=null)
        {
            swap(node);
            help(node.left);
            help(node.right);
        }
        return;
    }
    public TreeNode invertTree(TreeNode root) {
        help(root);
        return root;
    }
}
