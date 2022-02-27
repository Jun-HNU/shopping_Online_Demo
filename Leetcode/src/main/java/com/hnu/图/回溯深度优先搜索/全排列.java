package com.hnu.图.回溯深度优先搜索;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 全排列{




    public List<List<Integer>> permute2(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;

    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;//记录节点是否有被访问过
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> ls=new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {

//if(nums.length==0) return
        dfs(nums);


     return res;
    }
// 0 1
    public void dfs(int[] nums)
    {

        if(ls.size()>=nums.length)//当路径长度等于3时结束
        {
            res.add(new ArrayList<>(ls));//记录路径
            return;
        }
        //对于每一个当前节点，它都有nums.length中选择
        for (int i = 0; i < nums.length ; i++) {
            if(ls.contains(nums[i])) continue;//排除不合法的
            //当前节点记录下来
            ls.add(nums[i]);
            //进入下一层
            dfs(nums);
            //当前路径已经走完，撤销选择，继续回到节点遍历下一个选择
            ls.removeLast();
        }
    }

}
