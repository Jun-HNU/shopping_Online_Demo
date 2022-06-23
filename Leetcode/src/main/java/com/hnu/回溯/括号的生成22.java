package com.hnu.回溯;

import java.util.ArrayList;
import java.util.List;
/*
回溯的本质=dfs+状态重置
 */
public class 括号的生成22 {
    boolean addLeft = true;
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(result,n,new String(),0,0);
        return result;
    }

    private void dfs(List<String> result,int n,String sb,int left,int right){
        if(left > n || right > n || left < right) return;
        if(sb.length() == 2*n){
            result.add(sb.toString());
            return;
        }
        left++;
        dfs(result,n,sb+"(",left,right);
        left--;
        right++;
        dfs(result,n,sb+")",left,right);
        right--;
    }
}