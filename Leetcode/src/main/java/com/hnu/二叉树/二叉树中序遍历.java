package com.hnu.二叉树;


import javax.xml.bind.SchemaOutputResolver;
import java.io.PushbackInputStream;
import java.security.PublicKey;
import java.util.*;


public class 二叉树中序遍历 {



    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if(root != null)
            stack.push(root);

        // 思路 ：将访问的节点放入栈中，把要处理的节点也放入栈中， 紧接着放入一个空指针作为标记
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();    // 弹出栈顶元素
            // 如弹出元素不为空 则继续访问其孩子节点
            if(cur != null){
                //因为是后续遍历，所以要让后被处理的节点先入栈
                // 用的栈结构 所以入栈顺序要与遍历顺序相反 中序遍历：左根右，入栈时候是：右根左。
                if(cur.right != null)
                    stack.push(cur.right); // 添加非空右节点

                //调整下面两行位置即可完成前序中序遍历
                stack.push(cur);           // 再重新添加中节点
                //System.out.println(stack.size());
                stack.push(null);          // 中节点暂时未处理 加null标记

                //

                if(cur.left != null)
                    stack.push(cur.left);  // 添加非空左节点
            }else{

                //若弹出的元素为空表明，下一个节点为父节点的子树的节点已经全部入栈了。
                // 若弹出元素为空，则继续弹出下一个元素
                cur = stack.pop();          // 取出处理元素
                res.add(cur.val);           // 加入结果列表
            }
        }
        return res;
    }
    public static List<Integer> inorderTraversal2(TreeNode root) {


        Stack<TreeNode> stack=new Stack<>();//这里的栈就相当于一个缓存。
        LinkedList<Integer> lis=new LinkedList<>();
        TreeNode cur=root;
        while (null!=cur||!stack.isEmpty())
        {
            if(null!=cur)
            {
            stack.push(cur);
            cur=cur.left;
            }
            else
            {
                TreeNode pop = stack.pop();
                lis.add(pop.val);
                cur=pop.right;
            }

        }



        return lis;
    }


    public static List<Integer> Traversal(TreeNode root, List<Integer> it) {

        if (root == null) return it;
        //将list对象的引用通过，return返回，这样，这个引用对于所有的迭代的当前函数栈都是可见的。
        //函数栈，就是一个栈，先进后出的原则。

        Traversal(root.left, it);
        it.add(root.val);
        Traversal(root.right, it);
        return it;
    }


    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> it = new LinkedList<>();
        return Traversal(root, it);

    }
}