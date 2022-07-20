package com.hnu.广度优先搜索BFS;

import com.hnu.二叉树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最大深度 {


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
        public int maxDepth2(TreeNode root) {

            int max=0;
            if(null==root) return 0;
            Queue<TreeNode> ls= new LinkedList<>();
            ls.add(root);//
            while(!ls.isEmpty()){
                int size= ls.size();
                for (int i=0;i<size;i++)//将队列中前size个节点依次弹出，也就是当前层对应的节点
                {

                    TreeNode node = ls.poll();

                    if(node.left!=null)
                    {
                        ls.add(node.left);
                    }
                    if(node.right!=null)
                    {
                        ls.add(node.right);
                    }
                }
                //for 循环结束，即该层的节点已经完成遍历，但是该层的子节点已经放进了队列中，此时队列中的节点为下一层等待被遍历的节点
                max=max+1;
            }

            return max;
        }




    private static int max;
    public int maxDepth(TreeNode root) {
        max=0;
        if (root==null) return 0;
        help(root,1);//从第一层开始遍历
        return max;
    }

    void  help(TreeNode node, int l)
    {
        if(max<l) max=l;//记录最大的层数
        if(node.left!=null)
        {
            help(node.left,l+1);
        }
        if(node.right!=null)
        {
            help(node.right,l+1);
        }
    }

/*
自顶向下:上层数值将值传递给下层，直到最后一层停止递归
自底向上：上层数值依赖于下层数值，最后得到两个值（根的左子树的深度和根的右子树的深度）取最大值即可

 */
}
