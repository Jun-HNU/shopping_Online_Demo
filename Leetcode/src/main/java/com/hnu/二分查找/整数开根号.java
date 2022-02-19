package com.hnu.二分查找;
/*
给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 */

class 整数开根号 {
    public int mySqrt(int x) {

        if(x==0||x==1) return x;
        int left=1;
        int right=x;
        int mid=0;
        int M=0;
        //由于x小于2的32次方，所以开根号后小于2的16次方
        //int rightMax=(int)Math.pow(2,16);
        int rightMax=65536;
        if(x>rightMax)
           right=rightMax;//开根号后比2的16次方这个数小
        else
            right=x;
        while(left<=right)
        {
            mid=left+(right-left)/2;//避免超出整数的限制
            M=mid*mid;
            if(x<M||M<0)////由于允许的最大数是2的16次，那么仍然有可能平方后超出限制。
              right=mid-1;
            if(x>M) 
             left=mid+1;
            if(x==mid*mid)
                return mid;
        }
        if(right*right>x)
         return right-1;
        return right;
    }
}