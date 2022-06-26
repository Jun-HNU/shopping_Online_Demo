package com.hnu.动态规划DP;




/*
给你一个 n x n 整数矩阵 arr ，请你返回 非零偏移下降路径 数字和的最小值。

非零偏移下降路径 定义为：从 arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。




思路： 由于是从第一行的任意元素开始，向下非零偏移，求最小路径。
创建一个二维数据来保存到达每一行的当前元素时的最小路径和。
计算上一行非相邻的列到达当前位置的最小值，依次迭代到最后一行，最后计算最后一行的最小值即可。

 */



public class 下降路径最小和II1289  {
    public int min(int [] r,int j)
    {
        int min= Integer.MAX_VALUE;

        for (int i = 0; i < r.length; i++) {

            if(j==i)
            {
                i++;
                if(i==r.length)
                {
                    return min;
                }
            }

            if(r[i]<min) min=r[i];

        }


        return min;


    }

    public int minFallingPathSum(int[][] grid) {


        int n= grid.length;
        int m=grid[0].length;

        int [][] s= new int [n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if(i==0) s[i][j]=grid[i][j];
                else if(i>0)
                    s[i][j]= grid[i][j]+min(s[i-1],j);
            }

        }

        int minS=Integer.MAX_VALUE;
        for (int i = 0; i < s[n-1].length; i++) {


            if(s[n-1][i]<minS) minS=s[n-1][i];

        }
        return minS;

    }


    public static void main(String[] args) {
        //min2();
        int[][] arr = {{2,1,3},{4,5,6},{7,8,9}};

        minFallingPathSum2(arr);

    }

    //求数组top2的元素对应的下角标。



    public static int minFallingPathSum2(int[][] grid) {


        int n= grid.length;
        int m=grid[0].length;

        int [][] s= new int [n][m];

        for (int i = 0; i < n; i++) {
            int minFirst = -1;
            int minSecond = -1;


if(i>0) {


    int mF = Integer.MAX_VALUE;
    int mS = Integer.MAX_VALUE;



    for (int k = 0; k < s[i-1].length; k++) {

        //  123
        if (s[i-1][k] < mF) {
            mS = mF;
            mF = s[i-1][k];


            minSecond = minFirst;
            minFirst = k;

        } else if (s[i-1][k] < mS) {
            mS = s[i-1][k];

            minSecond = k;

        }


    }

}
           // System.out.println(minFirst);

            for (int j = 0; j < m; j++) {

                if(i==0) s[i][j]=grid[i][j];
                else if(i>0)
                {

                   if(j==minFirst)
                    s[i][j]= grid[i][j]+s[i-1][minSecond];
                   else
                       s[i][j]= grid[i][j]+s[i-1][minFirst];

                }
            }

        }

        int minS=Integer.MAX_VALUE;
        for (int i = 0; i < s[n-1].length; i++) {


            if(s[n-1][i]<minS) minS=s[n-1][i];

        }
        return minS;

    }











}