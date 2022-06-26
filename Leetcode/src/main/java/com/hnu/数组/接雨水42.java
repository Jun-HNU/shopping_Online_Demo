package com.hnu.数组;

public class 接雨水42 {


    public int trap(int[] height) {
        int s=0;
        int left=0;
        int right=height.length-1;
//4 9

        while (left+1<right)
        {

            while(height[left]==0&&left+1<right)
                if(left<height.length-1)
                    left++;
                if (left+1>=right) break;
            while(height[right]==0&&left+1<right)
                if(right>0)
                    right--;
            if (left+1>=right) break;
            for (int i = left; i <=right ; i++) {
                height[i] = height[i] - 1;
                if(height[i]==-1)
                {
                    s++;
                    height[i]=0;
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {

        System.out.println(new 接雨水42().trap(new int []{2,0,2}));
    }
}
