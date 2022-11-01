package com.hnu.二分查找;


/*\
如果可以开根号，如果是准确的，直接返回。否则按指定的精确度返回
 */
public class 根号N {


    public static void main(String[] args) {

        double n=8;
        int t=3;
        System.out.println(fun(n,t));;
        System.out.println(Math.sqrt(n));

    }
    static double fun(double x,int t)
    {
        if(x==0||x==1) return x;
        double l=0;
        double r=x/2;
        while (l<r)
        {
            int mid=(int)(l+(r-l)/2);

            if(x<mid*mid)
            {
                r=mid-1;
            }else if(x==mid*mid)
            {
                return mid;
            }
            else
            {
                l=mid;
            }

        }
        if(l*l==x)
            return l;

        l=0;
        r=x;
        double v=1;
        for (int i = 0; i <t ; i++) {
            v=v*10;
        }
        while (r-l>1/v)
        {
            double mid=l+(r-l)/2;
            if(x<mid*mid)//缩小右边界
            {
                r=mid;
            }else if(x==mid*mid)
            {
                return mid;
            }
            else//缩小左边界
            {
                l=mid;
            }

        }
        return l;
    }



}
