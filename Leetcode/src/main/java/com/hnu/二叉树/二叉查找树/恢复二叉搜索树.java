package com.hnu.二叉树.二叉查找树;

import com.hnu.二叉树.TreeNode;


//中序遍历可以确定一个一棵树是否为搜索二叉树。
//找到第一个异常的节点，找到最后一个异常的节点，将他们的值交换
public class 恢复二叉搜索树 {
    //3 2 1  1 2 3
    //1 3  2 4   1 2 3 4
    int max=Integer.MIN_VALUE;
    boolean FirsValueIsNotMIN=true;
    int i=0;
    TreeNode FirstNode;
    TreeNode SecondNode;
    TreeNode postNode;

    void swap(TreeNode a,TreeNode b)
    {
        int tem=a.val;
        a.val=b.val;
        b.val=tem;
    }

    public void help(TreeNode root) {

        if (null==root) return;
        help(root.left);
        i++;
        if(FirsValueIsNotMIN==true)
        {

            if(root.val==Integer.MIN_VALUE&&i==1) FirsValueIsNotMIN=false;
            if(root.val==Integer.MIN_VALUE&&i!=1)
            {
                SecondNode=root;//反复更新第二个节点
                return;
            }

        }

        if(root.val>max||(FirsValueIsNotMIN==false)&&i==1)
        {
            if(null!=FirstNode)
                return;
            max=root.val;
            postNode=root;
            System.out.println(root.val);

        }
        else {
            if(null==FirstNode)
            {
                FirstNode=postNode;//第一个节点指挥被赋值一次
                System.out.println(FirstNode.val);
            }
            SecondNode=root;//反复更新第二个节点

        }

        help(root.right);
    }


    public void recoverTree(TreeNode root) {
        help(root);
        System.out.println(FirsValueIsNotMIN);
        //System.out.println(SecondNode.val);
        // System.out.println(FirstNode.val);
        swap(FirstNode,SecondNode);
    }
}