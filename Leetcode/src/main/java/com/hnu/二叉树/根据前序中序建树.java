package com.hnu.二叉树;

import java.util.HashMap;
import java.util.Map;

/*
前序遍历：根节点，左子树，右子树
中序遍历：左子树，根节点，右子树
从前序数组中找到子树的根节点，也就是前序数组的第一个元素。
根据根节点的值找到中序根节点在中序数组中的位置（事先将数组值和索引存入hashMap，方便查询），根节点的左侧为左子树的区间，右侧为右子树的区间。

而子树的中序数组的长度和前序数组的长度是一样。
base case是由根节点 的值构建根节点，再迭代填充节点的左子树和右子树。


 */


public class 根据前序中序建树 {

    int[] preorder;
    int[] inorder;
    Map<Integer,Integer> indexDic;

    public TreeNode help(int preStart,int preEnd,int inSatrt,int inEnd)
    {

        //这里边界条件很重要否则会导致数组溢出
        if(preStart>preEnd||inSatrt>inEnd) return null;

        int value = this.preorder[preStart];
        int indexInInorder = indexDic.get(value);//这里得到根节点在中序数组中的索引，它的左侧为左子树，右侧为右子树。


        TreeNode node = new TreeNode(value);
        node.left=help(
                preStart+1,//左子树前序开始的位置
                preStart+1+(indexInInorder-1-inSatrt),//左子树结束的位置
                inSatrt,//左子树后续开始的位置
                indexInInorder-1//左子树后续结束的位置
        );//indexInInorder-1 - inStart 为子数组的区间长度，前序数组的区间长度和中序数组的区间长度一样。


        node.right=help(
                preStart+1+(indexInInorder-1-inSatrt)+1,
                preEnd,
                indexInInorder+1,
                inEnd
        );
        return node;

    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.inorder=inorder;
        this.preorder=preorder;


        indexDic= new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexDic.put(inorder[i],i);
        }

        TreeNode treeNode = help(0, preorder.length - 1, 0, inorder.length - 1);


        return treeNode;


    }
}