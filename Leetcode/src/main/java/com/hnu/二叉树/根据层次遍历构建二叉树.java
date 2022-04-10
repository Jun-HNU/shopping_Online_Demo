package com.hnu.二叉树;


import java.lang.reflect.Array;
import java.util.*;

//leetcode中二叉树的题目，测试用例一般给出的是层次遍历后的value值存放在数组中.
public class 根据层次遍历构建二叉树 {






    public  static TreeNode buildTree(Integer [] r)
    {
        Queue<TreeNode> q= new LinkedList<>();
        if (r.length==0||r[0]==null) return null;


        int k=0;
        boolean left=true;
        TreeNode root = new TreeNode(r[0]);
        q.add(root);
            while(!q.isEmpty())
            {
                int size=q.size();
                for (int j = 0; j < size; j++) {//遍历当前层的节点
                    TreeNode cur = q.poll();

                    for (int i = 0; i <2 ; i++) {//构建当前层每个节点的子节点
                        k++;
                        if(k>=r.length)
                            return root;

                        if(r[k]!=null)
                        {
                            TreeNode node = new TreeNode(r[k]);
                            if(left==true)
                                cur.left=node;
                            else
                                cur.right=node;
                            q.add(node);
                        }
                        left=!left;
                    }
                }
            }
        return root;
        
    }




    public static void main(String[] args) {

        Integer [] b= new Integer []{ 1,3,null,null,2};
        List<Integer> ls= new LinkedList<>(Arrays.asList(1,3,null,null,2));

        TreeNode node = buildTree(b);

        for (int i = 0; i <ls.size() ; i++) {
            System.out.println(ls.get(i));
        }
    }
}
