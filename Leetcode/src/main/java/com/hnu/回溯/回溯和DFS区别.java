package com.hnu.回溯;


import com.hnu.二叉树.TreeNode;

public class 回溯和DFS区别 {



    // DFS 算法，关注点在节点

    void traverse(TreeNode root) {
        if (root == null) return;
        System.out.println("进入节点 %s"+ root);
        for (TreeNode child : root.children) {
            traverse(child);
        }
        System.out.println("进入节点 %s"+ root);
    }

    // 回溯算法，关注点在树枝
    void backtrack(TreeNode root) {
        if (root == null) return;
        for (TreeNode child : root.children) {
            // 做选择
            System.out.println("从 %s 到 %s"+ root+child);
            backtrack(child);
            // 撤销选择
            System.out.println("从 %s 到 %s"+ child+ root);
        }
    }
}
