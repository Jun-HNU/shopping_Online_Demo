package com.hnu;

import java.util.HashMap;

public class 两数之和 {
    public static int[] twoSum1(int[] nums, int target) {

    int [] result =new int [2] ;
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
            if(target-nums[i]==nums[j])
            {
                result[0]=i;
                result[1]=j;
            }
            }

        }

        return result;

    }
    public int[] twoSum2(int[] nums, int target) {
        int [] result =new int [2] ;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if(map.containsKey(target-nums[i]))
            {
                result[0]=i;
                result[1]=map.get(target-nums[i]);
            }
            else
            {
                map.put(nums[i],i);
            }

        }

        return result;
    }
    public static void main(String[] args) {
      int  [] nums = {2,7,11,15};
        int target = 9;

        twoSum1(nums,target);

    }
}
