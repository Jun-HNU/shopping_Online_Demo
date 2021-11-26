package com.hnu;

import java.awt.print.PrinterGraphics;

public class 反转二叉树 {

    public static TreeNode fun(TreeNode head)


    {

        if(null!=head){
            TreeNode cur=head.left;


            fun(head.left);

            fun(head.right);


        }




        return null;

    }







}
