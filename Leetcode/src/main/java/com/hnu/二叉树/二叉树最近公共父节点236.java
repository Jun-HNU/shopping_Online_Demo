package com.hnu.二叉树;

public class 二叉树最近公共父节点236 {

/*
提示：

树中节点数目在范围 [2, 105] 内。
-109 <= Node.val <= 109
所有 Node.val 互不相同 。
p != q
p 和 q 均存在于给定的二叉树中。
*/

    public  TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {



        return help(root,p,q);

//通过前序遍历，分别遍历左子树和右子树。

//base case 就是，  ”遍历节点“  就是当前p或者q.

    }


    //节点数目不小于2，p和q是不同节点，p和q在给定的二叉树中
    public static TreeNode help(TreeNode root, TreeNode p, TreeNode q)
    {

       //不在此分支
        if (root==null) return root;
        if(root==p||root==q) return root;
//遍历左子树，如果至少有一个节点在左子树，则返回值不为空
        TreeNode left = help(root.left, p,q);
//遍历右子树，如果至少有一个节点在右子树，则返回值不为空
        TreeNode right= help(root.right,p,q);


        //如果两个节点分别在当前的节点左右子树，则返回当前节点
        if(left!=null&&right!=null) return root;

        if(left!=null) return left;//全都在当前的左子树一侧
        if(right!=null) return right;//全都在当前的右子树
        return null;

    }
}
