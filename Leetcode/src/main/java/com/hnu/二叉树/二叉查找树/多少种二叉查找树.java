package com.hnu.二叉树.二叉查找树;


/*

二叉搜索树（Binary Search Tree，后文简写 BST）相关的文章，手把手带你刷 BST。

首先，BST 的特性大家应该都很熟悉了：

1、对于 BST 的每一个节点node，左子树节点的值都比node的值要小，右子树节点的值都比node的值大。

2、对于 BST 的每一个节点node，它的左侧子树和右侧子树都是 BST。

二叉搜索树并不算复杂，但我觉得它构建起了数据结构领域的半壁江山，直接基于 BST 的数据结构有 AVL 树，红黑树等等，拥有了自平衡性质，可以提供 logN 级别的增删查改效率；还有 B+ 树，线段树等结构都是基于 BST 的思想来设计的。

从做算法题的角度来看 BST，除了它的定义，还有一个重要的性质：BST 的中序遍历结果是有序的（升序）。


 */
public class 多少种二叉查找树 {


    /*
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
    返回满足题意的二叉搜索树的种数。
    1 <= n <= 19
     */



        public static int [][] mem=null;
        public int help(int lo,int hi)
        {
            //base case
            // 如果 为1，表示空，也是一种
            if(lo>hi) return 1;
            if(mem[lo][hi]!=0) return mem[lo][hi];
            int res=0;
            for (int i = lo; i <=hi; i++) {
                int left=help(lo,i-1);
                int right=help(i+1,hi);
                res+=left*right;
            }
            mem[lo][hi]=res;
            return res;
        }


        public int numTrees(int n) {
        mem= new int [n+1][n+1];
        return help(1,n);//根据题目n大于1.
    }



    public int numTrees2(int n) {
        // dp讲解
        // 设置函数G(n)为n个节点的不同二叉上搜索树的种类
        // 设置f(n)为以节点[1, n]为根节点的BST种类
        // ==> 推导G(n) = f(1) + f(2) + f(3) +....+ f(n)
        // 假设以3为节点,3的左节点有1和2，有节点有[4, n]个
        // ==> 推导f(i) 取决于左右子树的种类,一对多 f(i) = G(i-1) * G(n - i)

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <=n; i++) {
            // G(n) = f(1) + f(2) + ....
            // 循环i次
            int sum = 0;
            for (int j = 1; j <= i; j++) {
                // f(i) = G(i-1) * G(n - i)
                // 初始dp[i] = 0
                sum += dp[j - 1] * dp[i- j];
            }
            dp[i] += sum;
        }
        return dp[n];
    }



}
