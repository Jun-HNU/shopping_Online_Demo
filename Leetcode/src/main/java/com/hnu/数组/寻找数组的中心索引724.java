package com.hnu.数组;

public class 寻找数组的中心索引724 {


    //[1, 7, 3, 6, 5, 6]
    public int pivotIndex(int[] nums) {

        int s=0;

        for (int i = 0; i <nums.length ; i++) {
            s=s+nums[i];
        }


        int cur=0;
        //假设i为中点
        for (int i = 0; i <nums.length ; i++) {

            if(i>0)
            cur=cur+nums[i-1];
            s=s-nums[i];
            if(cur==s) return i;

        }

        return -1;

    }

}
