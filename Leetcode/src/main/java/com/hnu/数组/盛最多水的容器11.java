package com.hnu.数组;

public class 盛最多水的容器11 {
/*
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */
    public int maxArea(int[] height) {


        int S=0;
        int len=height.length-1;
        int i=0;
        int j=len;
        while (i<j)//容器的底的最大值是直到的。在底减小的同时，寻找边尽可能的大。
        {
            int tem=(j-i)*Integer.min(height[i],height[j]);
            if(tem>S) S=tem;
            if(height[i]>height[j]) //保留底最大的一个边
                j--;
            else i++;

        }
    return S;
    }
}
