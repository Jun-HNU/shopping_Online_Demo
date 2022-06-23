package com.hnu.二分查找;


/*
输入: nums = [1,3,5,6], target = 2
输出: 1
 */
public class 搜索插入位置35 {
    public int searchInsert(int[] nums, int target) {

        int left=0;
        int right=nums.length-1;


        while(left<=right)

        {
            int mid=left+(right-left)/2;
            if(target==nums[mid]) return mid;

            //如果从这里跳出while循环，则一定有target<nums[right+1]
            if(target<nums[mid]) right=mid-1;
            //如果从这里跳出while循环，则一定有target>nums[left-1]
            if(target>nums[mid]) left=mid+1;

            //target<nums[right+1]
            //target>nums[left-1]

        }
        //System.out.println(left+"  "+right);
        //根据上while循环结束的边界，倒退
        if(right+1<nums.length)//如果是从target>nums[mid]跳出循环，这里的可能会有数组越界
            if(target<nums[right+1])
                //right+1前面有right个数，所以下一个数角标必须是right+1
                return right+1;

        if(left-1<nums.length)
            if(target>nums[left-1])
                //left-1之后的一个数，角标直接为left
                return left;
        return left;



    }
}
