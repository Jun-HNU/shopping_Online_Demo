package com.hnu.排序;

public class 求数组的top2的元素的下角标 {

    public  static void min2(int [] r)
    {


        int mF= Integer.MAX_VALUE;
        int mS= Integer.MAX_VALUE;


        int minFirst=-1;
        int minSecond=-1;

        for (int i = 0; i < r.length; i++) {

            //  123
            if(r[i]<mF)
            {
                mS=mF;
                mF=r[i];


                minSecond=minFirst;
                minFirst=i;

            }
            else
            if(r[i]<mS)
            {
                mS=r[i];
                minSecond=i;

            }



        }

        System.out.println(minFirst+" "+minSecond);



    }
}
