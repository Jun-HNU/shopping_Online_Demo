package com.hnu;

public class 数组非0数前移 {


    /*
    给一个整型数组，里面有若干个0，其余为非0元素，将0移到后面，其余元素的相对顺序不变
    ，如[1,0,2,3,0,4]变成[1,2,3,4,0,0],要求in space

     */

    public static  int [] fun3(int [ ] x){

        int s=0;
        int  q=0;
        int j=0;
        for (; j < x.length-1; j++)
        {
            if(x[j]!=0) {
                if((s!=j))
                {
                    x[s]= x[j];
                    x[j]=0;
                }

                s++;
            }
            else
            {
                q++;
            }
        }
        System.out.println(j+" i am here");

        return x;
    }

    public static  int [] fun2(int [ ] x){

                  int s=0;
int j=0;
        for (; j < x.length-1-s; j++)
        {
            if(x[j]==0) {
                for (int i = j; i < x.length - 1; i++) {

                    x[i] = x[i + 1];
                }
                x[x.length-1]=0;
                s++;
            }
        }
        System.out.println(j+" i am here");

        return x;
    }

    public static void main(String[] args) {
        int [] y= new int [] {1,0,2,3,0,4,0};
        int [] x=fun3(y);
        for (int i = 0; i <x.length ; i++) {
            System.out.println(x[i]);

        }



    }


    public static  int [] fun(int [ ] x){

           int [] y=  new int [x.length];
        int j=0;

        for (int i = 0; i < x.length; i++) {
            if(x[i]!=0)
            {
                y[j++]=x[i];
            }

        }


         return y;
    }


}
