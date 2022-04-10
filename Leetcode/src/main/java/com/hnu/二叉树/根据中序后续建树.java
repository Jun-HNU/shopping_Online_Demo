package com.hnu.二叉树;


import java.util.HashMap;

/*

1、
2、
3、，我觉得这一点很重要，这一点清晰才能得出左右子树起点和起点

中序遍历为 左子树 根 右子树，中序遍历能区分出左右子树
后序遍历为 左子树 右子树 根 ，后序遍历的最后一个一定是根节点
通过后序遍历拿到每棵子树中间的根节点，再将中序遍历和后序遍历两个
数组划分为左子树和右子树按照中序和后序分别对应的两个数组(共四个数组)，
再进行自递归即可。中序遍历和后序遍历的左右子树的长度一定是相等


前序中左起第一位1肯定是根结点，我们可以据此找到中序中根结点的位置rootin；

中序中根结点左边就是左子树结点，右边就是右子树结点，
即[左子树结点，根结点，右子树结点]，我们就可以得出左子树结点个数为int left = rootin - leftin;；

前序遍历节点点分布应该是：[根结点，左子树结点，右子树结点]；
根据前一步确定的左子树个数，可以确定前序中左子树结点和右子树结点的范围；
前序遍历节点点分布应该是：[左子树，右子树结点，根结点]；
如果我们要前序遍历生成二叉树的话，下一层递归应该是：
左子树：root->left = pre_order(前序左子树范围，中序左子树范围，前序序列，中序序列);；
右子树：root->right = pre_order(前序右子树范围，中序右子树范围，前序序列，中序序列);。
每一层递归都要返回当前根结点root；


 */
public class 根据中序后续建树 {

    HashMap<Integer,Integer> inorderArrayMap = new HashMap<>();
    int[] post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0;i < inorder.length; i++) {//将节点值和下角标全部存入map.
            inorderArrayMap.put(inorder[i], i);//妙啊！将节点值及索引全部记录在哈希表中。
            //方便后面在后续数组中找到中节点的值后，在这个map中找到下角标，在中序数组中根据中节点将数组分为左子树和右子树
        }

        post = postorder;
        TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
        return root;
    }

    public TreeNode buildTree(
            int inorderStart, //中序数组的开始坐标
            int inorderEnd, //中序数组的结束坐标
            int postorderStart,//后序数组的开始坐标
            int postorderEnd//后序数组的结束坐标
    ) {
        if(inorderEnd < inorderStart || postorderEnd < postorderStart) return null;

        int root = post[postorderEnd];//根据后序遍历结果，取得根节点的值
        //根节点在中序数组的角标，它的左侧为左子树节点值集合，右侧为右子树节点值集合
        int rootIndexInInorderArray = inorderArrayMap.get(root);
//对应postoder 数组中角标为postorderEnd元素，也是inorder数组中角标为rootIndexInInorderArray的元素

       /*
       中序数组
       {
        左子树  [inorderStart,rootIndexInInorderArray - 1],
       根      rootIndexInInorderArray,
       右子树 [rootIndexInInorderArray + 1,inorderEnd]
       }
       ((rootIndexInInorderArray -1) -inorderStart)为区间跨度
        */
        /*后续数组
        {
       左子树 [postorderStart,postorderStart +((rootIndexInInorderArray -1) -inorderStart)],
       右子树[postorderStart + ((rootIndexInInorderArray-1) - inorderStart)+1,postorderEnd - 1],
       根   postorderEnd
       }
         */
        TreeNode node = new TreeNode(root);//创建树的当前节点,子树/树的根节点。
        //构建当前树的左节点
        node.left = buildTree(
                /*inoder1*/     inorderStart, //左子树中中序数组的开始的坐标
                /*inoder2*/    rootIndexInInorderArray - 1,//左子树中序数组的结束的坐标

               /*post1*/  postorderStart,//左子树后序数组的开始的坐标
               /*post2*/     postorderStart +((rootIndexInInorderArray -1) -inorderStart) );//左子树后序数组的结束的坐标
        //构建当前树的右节点
        node.right = buildTree(
                /*inoder3*/  rootIndexInInorderArray + 1,//右子树中中序数组的开始的坐标
                /*inoder4*/  inorderEnd, //右子树中序数组的结束的坐标

                /*post3*/      postorderStart + ((rootIndexInInorderArray-1) - inorderStart)+1,//右子树后序数组的开始的坐标
                /*post4*/     postorderEnd - 1//右子树后序数组的结束的坐标
        );
        return node;//注意！返回的是新建的node！
    }
}