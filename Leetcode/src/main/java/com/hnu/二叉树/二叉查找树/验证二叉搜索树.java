package com.hnu.二叉树.二叉查找树;

import com.hnu.二叉树.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;


/*
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

树中节点数目范围在[1, 104] 内
-231 <= Node.val <= 231 - 1
 */
public class 验证二叉搜索树 {
        //判断一个序列是否为查找二叉树，只需要中序遍历。
        //如果存在重复返回false
        //如果后弹出的数比之前大则返回false
        public static boolean isValidBST(TreeNode root) {
        int max= Integer.MIN_VALUE;
        int i=0;
        Stack<TreeNode> s= new Stack<>();
        if (root==null||(null == root.right)&&(null == root.left)) return true;
        s.push(root);
        while (!s.isEmpty())
        {
            TreeNode node = s.pop();
            if(node!=null) {//
                if (null != node.right) {
                    s.push(node.right);
                }
                s.push(node);
                s.push(null);//压入一个空元素做标记
                if (null != node.left) {
                    s.push(node.left);
                }
            }
            else
            {//进入这里，说明前面弹出的元素为null,继续弹出栈顶元素
                TreeNode pop = s.pop();

                if(pop.val>max||(pop.val==Integer.MIN_VALUE)&&i==0)
                {
                    i++;
                    max=pop.val;
                }
                else
                    return false;

            }
        }

        return true;

    }


    //[2147483647,null,-2147483648]
    public static void main(String[] args) {
        isValidBST(new TreeNode(-2147483648));


    }


}
