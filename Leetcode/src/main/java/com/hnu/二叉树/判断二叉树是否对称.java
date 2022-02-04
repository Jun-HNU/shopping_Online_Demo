package com.hnu.二叉树;

public class 判断二叉树是否对称 {

    /*
    递推公式
node.left        node.right
node.left.left   node.right.right
node.left.right  node.right.left
     */


    public  boolean  help(TreeNode left,TreeNode right){
        boolean ss=true;
        boolean dd=true;
        
        if(left.left==null&&right.right==null) ss=true;
        else
        if(left.left!=null&&right.right!=null&&left.left.val==right.right.val)
        {
            ss= help(left.left,right.right);
        }
        else
            ss=false;

        if(left.right==null&&right.left==null) dd=true;
        else
        if(left.right!=null&&right.left!=null&&left.right.val==right.left.val)
        {
            dd= help(left.right,right.left);
        }
        else
            dd=false;

        return ss&&dd;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root.left==null&&root.right==null) return true;
        if(root.left!=null&&root.right!=null&&root.left.val==root.right.val)
            return help(root.left,root.right);
        return false;
    }


}
