package com.hnu.二分查找;

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