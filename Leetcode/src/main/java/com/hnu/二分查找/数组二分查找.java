package com.hnu.二分查找;


/*
二分法能解决四种问题：
（1）有序数组查找某个值是否存在。
 （2）有序数组查找大于等于某个值的最左侧。 eg：【1，2，2，2，2】找出大于等于2的元素最左侧下标。
 （3）有序数组查找小于等于某个值的最右侧。 eg：【1，2，2，2，2】找出小于等于2的元素最右侧下标。
  （4）局部最值问题。依据决策条件，不断更新区间的左或右边界，知道找到
 */



import javax.xml.bind.SchemaOutputResolver;

class 数组二分查找 {
    public int search(int[] nums, int target) {
        int hight=nums.length-1;
        int low=0;
        int mid=0;
        while(low<=hight)
        {
            mid=(hight+low)/2;
            if(target==nums[mid])  return mid;
            if(target<nums[mid] ) hight=mid-1;
            if(target>nums[mid]) low=mid+1;
        }

        return -1;
    }
}