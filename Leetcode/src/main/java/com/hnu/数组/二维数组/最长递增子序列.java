package com.hnu.数组.二维数组;

class 最长递增子序列 {
    public int lengthOfLIS(int[] nums) {
        if(nums.length==1) return 1;
        int max=0;
        int [] c=new int[nums.length];
        for(int i=1;i<nums.length;i++)
        {

            for(int j=0;j<i;j++)
            {
                if(nums[i]>nums[j])
                    if(c[i]<c[j]+1)
                    {

                        c[i]=c[j]+1;
                        if(c[i]>max) max=c[i];
                    }

            }


        }

        return max+1;

    }
}