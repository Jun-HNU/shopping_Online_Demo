package com.hnu.二叉树;

import java.util.LinkedList;
import java.util.List;

public class 路径总和 {

  public static   List<Integer> ls=new LinkedList<>();
   public static void help(TreeNode node,int n)
    {
            n=node.val+n;

        if(node.left==null&&node.right==null)
            ls.add(n);
            if(node.left!=null)
            {

             help(node.left,n);

            }
            if (node.right!=null)
            {
               help(node.right,n);
            }

    }



    public  static boolean hasPathSum(TreeNode root, int targetSum) {

        if(root!=null)
        {
            help(root, 0);

        }

        if(ls.size()==0) return false;

        for (int i = 0; i <ls.size() ; i++) {
            if (ls.get(i)==targetSum)
                return true;

        }
      return false;

    }
}
