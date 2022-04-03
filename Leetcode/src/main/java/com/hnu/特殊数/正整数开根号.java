package com.hnu.特殊数;

public class 正整数开根号 {

    public static void main(String[] args) {
        System.out.println(fun(6));
        System.out.println(fun(9));
        //fun(9);
    }
    public static int fun(int a){

        if(a<1) return 0;


        int max=a;
        int min=0;
        int mid=0;
        int result=0;
        while(min<=max)
        {
           // mid=(max+min)/2;
            mid=min+(max-min)/2;//避免数字溢出
            result=mid*mid;
            if(result==a)
                return mid;
            if(result<a)
            {
                min=mid+1;
            }
            if(result>a)
            {
                max=mid-1;
            }

        }
        return -1;

    }

}
