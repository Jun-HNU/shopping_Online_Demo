package com.hnu.动态规划DP.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;





public class 求子集 {

    public static  List<List<Integer>> res= new LinkedList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        if(nums.length==0) return res;

        List<Integer> ls= new LinkedList<>();
        def(nums,0,ls);
        return res;

    }
    public static void def(int[] nums,int start,List<Integer> ls)
    {

        //前序遍历
        if(ls!=null)


            res.add(new ArrayList<>(ls));//记录路径
        if(start>=nums.length) return;//遍历到最后一层退出
        for (int i = start; i <nums.length ; i++) {
            ls.add(nums[i]);
            def(nums,i+1,ls);//遍历下一层
            ls.remove(ls.size()-1);//回溯
        }

    }
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int all = res.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = new int [] {1,4,7};

        List<List<Integer>> res = subsets(nums);
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j <res.get(i).size() ; j++) {
                System.out.print(res.get(i).get(j)+" ");
            }
            System.out.println();

        }

    }
}
