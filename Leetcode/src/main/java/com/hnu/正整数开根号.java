package com.hnu;

public class 正整数开根号 {

    public static void main(String[] args) {
        System.out.println(fun(6));
        System.out.println(fun(9));
        //fun(9);
    }
    public static int fun(int a){

        if(a<1) return 0;
        /*for(int i=0;i<a/2;i++)
        {
            if(i*i==a)
            {
                return i;
            }
        }*/

        int max=a;
        int min=0;
        int mid=0;
        int result=0;
        while(min<max)
        {
            mid=(max+min)/2;
            //System.out.println(mid);
            if(max==min||max==min+1)
                return -1;
            result=mid*mid;
            if(result==a)
                return mid;
            if(result<a)
            {
                min=(max+min)/2;
                /*if(min>=a/2)
                    break;*/
            }
            if(result>a)
            {
                max=(max+min)/2;
                /*if(max>=a/2)
                    break;*/
            }

        }
        return 0;

    }

}
