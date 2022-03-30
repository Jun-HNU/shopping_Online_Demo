package com.hnu.二叉树;


import java.util.HashMap;

/*

1、
2、
3、，我觉得这一点很重要，这一点清晰才能得出左右子树起点和起点

中序遍历为 左子树 中 右子树，中序遍历能区分出左右子树
后序遍历为 左子树 右子树 中 ，后序遍历的最后一个一定是根节点
通过后序遍历拿到每棵子树中间的根节点，再将中序遍历和后序遍历两个
数组划分为左子树和右子树按照中序和后序分别对应的两个数组(共四个数组)，
再进行自递归即可。中序遍历和后序遍历的左右子树的长度一定是相等



 */
public class 根据中序后续建树 {
}

class Solution {
    HashMap<Integer,Integer> inorderArrayMap = new HashMap<>();
    int[] post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0;i < inorder.length; i++) {
            inorderArrayMap.put(inorder[i], i);//妙啊！将节点值及索引全部记录在哈希表中。
            //方便后面在后续数组中找到中节点的值后，在中序数组中根据中节点将数组分为左子树和右子树
        }

        post = postorder;
        TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
        return root;
    }

    public TreeNode buildTree(
            int inorderStart, //中序数组的开始坐标
            int inorderEnd, //终须数组的结束坐标
            int postorderStart,//后续数组的开始坐标
            int postorderEnd//后续数组的结束坐标
    ) {
        if(inorderEnd < inorderStart || postorderEnd < postorderStart) return null;

        int root = post[postorderEnd];//根据后序遍历结果，取得根节点
        int rootIndexInInorderArray = inorderArrayMap.get(root);//获取对应的索引

        TreeNode node = new TreeNode(root);//创建树的当前节点。
        node.left = buildTree(inorderStart, //左子树中中序数组的开始的坐标
                rootIndexInInorderArray - 1,//左子树后序数组的结束的坐标
                postorderStart,//左子树后序数组的开始的坐标
                postorderStart + rootIndexInInorderArray - inorderStart - 1);//左子树后序数组的结束的坐标
        node.right = buildTree(rootIndexInInorderArray + 1,//右子树中中序数组的开始的坐标
                inorderEnd, //右子树中序数组的结束的坐标
                postorderStart + rootIndexInInorderArray - inorderStart,//右子树后序数组的开始的坐标
                postorderEnd - 1//右子树后序数组的结束的坐标
        );
        return node;//注意！返回的是新建的node！
    }
}