package com.hnu.二叉树;

import com.sun.deploy.panel.ITreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树层次遍历 {

    /*
    一层一层的，从左到右，从上到下，
    记录当前层的节点到一个队列中。
    遍历当前层的每个节点时，每遍历一个节点，用一个list记录当前层的节点值
    并判断它否有子节点，如果有则将它放到下一层的队列中。



     */


/*
借助队列的方法
 */
public static List< List<Integer>> levelOrder(TreeNode root)
    {

        List<List<Integer>> ls=new LinkedList<>();

        //Queue<TreeNode> q=new ArrayDeque<>();
        Queue<TreeNode> q=new LinkedList<>();

        if (root==null) return ls;
        q.add(root);
        while(!q.isEmpty())
        {
            int curQueSize=q.size();//队列中的前curQueSize个节点是当前层的
            List<Integer> l=new LinkedList<>();//创建一个list来记录当前层，所有节点的value
            for(int i=0;i<curQueSize;i++) {

                TreeNode node = q.poll();//弹出队列头部的元素

                if (node.left != null) {
                    q.add(node.left);//记录左子树，下一轮再操作
                }
                if (node.right != null) {
                    q.add(node.right);//记录左子树，下一轮再操作
                }

               l.add(node.val);
            }
            ls.add(l);//将当前层对应的list记录到大的list中


        }





     return ls;
    }



    /*
    递归的方法
     */

    private static List< List<Integer>> ls ;
    public List< List<Integer>> levelOrder2(TreeNode root){
        ls = new LinkedList<>();//执行当前函数时返回一个list
        if (root==null) return ls;
        help(root,0);//从第0层开始
        return ls;


    }
    public void help(TreeNode node, int levelNum)
    {
        if(ls.size()<levelNum+1){//当当前数组的长度size小于层数时，添加一个list，用于存放该层的value。
            List<Integer> l=new LinkedList<>();
            ls.add(l);
        }
        if(null!=node)
        {
            ls.get(levelNum).add(node.val);
        }
        if(null!=node.left)
        {

            help(node.left,levelNum+1);
        }

        if(null!=node.right)
        {
            help(node.right,levelNum+1);
        }
    }

}
