package com.hnu;

public class 最大子数组和 {

    public int maxSubArray(int[] nums) {

        int curSum=Integer.MIN_VALUE;
        int curMax=Integer.MIN_VALUE;

        int start =0;
       // Map<Integer,ArrayList> m=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {

            if(nums[i]>=0)//第一个不小于0 的数
            {
                if(start==0)
                {

                    start=1;//记录标志
                    curSum=nums[i];
                    curMax=nums[i];//当前和不小于0
                }

            }
            else
            {

                if(start==0)//没找到之前，只找最大的数
                {

                    if(nums[i]>curSum)
                    {
                        curSum=nums[i];
                        curMax=nums[i];
                    }

                }

            }
            if(start>0)//继续找最大和
            {

                if(start!=1)
                {
                    int tmp = curSum + nums[i];

                    if(tmp>0)
                    {

                        curSum =tmp;
                        if(nums[i]>=0)//curSum才有可能大于curMax
                        {
                            if(curSum>=curMax)
                                curMax=curSum;
                            System.out.println(curMax);
                        }


                    }
                    else
                    {
                        curSum=0;//归0，舍弃之前的计数
                    }
                }
                start=2;


            }

        }

        return  curMax;
    }

}