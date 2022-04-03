package com.hnu.二叉树.二叉查找树;

import com.hnu.二叉树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/*
搞清楚目的：构造二叉搜索树，且跟进不同的根结点构造不同的二叉搜索树；
定义：这决定了我们的代码要执行的action；
众所周知，二叉搜索树的定义是，左子树 < 根 < 右子树；且题目给的各个节点，是从小到达有序的
那么 从1 ～ n，随机选择k做根节点，那么 左子树一定是 1 ～ k-1, 右子树一定是 k+1 ~ n


 */



public class 输出所有二叉搜索树的序列 {

    //定义一个备忘录对象
    public static List<LinkedList<LinkedList<TreeNode>>> mo= new LinkedList<>();

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();

        // 构造闭区间 [1, n] 组成的 BST
       // 初始化一个二维数组，a[i][j]的值为一个LinkedList<TreeNode>类型的object.
        for (int i = 0; i <n+1 ; i++) {
            LinkedList<LinkedList<TreeNode>> lslsTreNode = new LinkedList<>();
            for (int j = 0; j < n+1; j++) {
                lslsTreNode.add(null);
            }
            mo.add(lslsTreNode);
        }

        return build(1, n);
    }

    /* 构造闭区间 [lo, hi] 组成的 BST */
    public static List<TreeNode> build(int lo, int hi) {
        LinkedList<TreeNode> res = new LinkedList<>();

        // base case
        if (lo > hi) {
            res.add(null);
            return res;
        }

        //如果之前计算过这种情形就直接返回
        if(null!=mo.get(lo).get(hi))
            return mo.get(lo).get(hi);
        // 1、穷举 root 节点的所有可能。
        for (int i = lo; i <= hi; i++) {//当前节点作为头节点
            // 2、递归构造出左右子树的所有合法 BST。
            //由于题目求的时查找二叉树的有哪些类型，
            //如果当根点值为i,那么左子树所有节点值肯定比i小,所以左子树的区间为[lo，i-1]
            List<TreeNode> leftTree = build(lo, i - 1);
            //如果当根节点值为i,那么右子树所有节点值肯定比i大,所以左子树的区间为[i+1，hi]
            List<TreeNode> rightTree = build(i + 1, hi);
            // 3、给 root 节点穷举所有左右子树的组合。
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i 作为根节点 root 的值
                    TreeNode root = new TreeNode(i);
                    //将left ,作为root的左子树
                    root.left = left;
                    //将right作为root的右子树
                    root.right = right;
                    res.add(root);
                }
            }

        }
        mo.get(lo).set(hi,res);
        return res;
    }

    public static void main(String[] args) {

        List<TreeNode> treeNodes = generateTrees(4);

        System.out.println(treeNodes);
    }


}




class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        // 构造闭区间 [1, n] 组成的 BST
        return build(1, n);
    }

    /* 构造闭区间 [lo, hi] 组成的 BST */
    List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();
        // base case
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 1、穷举 root 节点的所有可能。
        for (int i = lo; i <= hi; i++) {
            // 2、递归构造出左右子树的所有合法 BST。
            List<TreeNode> leftTree = build(lo, i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);
            // 3、给 root 节点穷举所有左右子树的组合。
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i 作为根节点 root 的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
