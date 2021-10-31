package com.hnu.二维数组;

import java.util.Arrays;

class 合并两个有序数组 {


    public static void main(String[] args) {
        int [] nums1={1,2,3,0,0,0};

        int [] nums2=  {2,5,6};
        //merge(nums1, 3, nums2, 3);
        merge2(nums1, 3, nums2, 3);

        for (int i=0;i<nums1.length;i++)
        {
            System.out.print(nums1[i]);
        }

    }


    public static void merge2(int[] nums1, int m, int[] nums2, int n) {



        for(int i=m,j=0;i<m+n;i++)
            nums1[i]=nums2[j++];

        Arrays.sort(nums1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int i=0;
    int j=0;
   int l=0;
while((i<m+n)&&(n>0)) {


    if (j == n) {
        j = n - 1;
    }

    if (i < m + l) {

        if (nums1[i] > nums2[j]) {
            l++;

            for (int k = m + l - 1; k > i; k--) {
                nums1[k] = nums1[k - 1];
            }
            nums1[i] = nums2[j];


            if (j == n - 1) {
                break;
            }

            j++;
        }
    }
    else{
        nums1[i] = nums2[j];
        j++;
    }
    i++;
}
        
        

    
}
}