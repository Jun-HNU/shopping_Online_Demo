package com.hnu.动态规划;

public class 最小路径和931 {




    public int minFallingPathSum(int[][] matrix) {
        int row= matrix.length;
        int lo=matrix[0].length;
        int [][] s= new int [row][lo];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < lo ; j++) {

                //下面全都是else if,每个if语句只会进入一次。
                //因为 先是遍历i，后遍历j，所以先判断i==0,再判断j==0.
                if(i==0) s[i][j]=    matrix[i][j];//第一行
                else if(j==0)   s[i][j]=    matrix[i][j]+Math.min(s[i-1][j],s[i-1][j+1]);
                else if(j==lo-1)   s[i][j]=    matrix[i][j]+Math.min(s[i-1][j-1],s[i-1][j]);
                else
                    s[i][j]=    matrix[i][j]+Math.min(Math.min(s[i-1][j-1],s[i-1][j]),s[i-1][j+1]);
            }

        }

        int min=Integer.MAX_VALUE;
        for (int i = 0; i < lo ; i++) {
            if(s[row-1][i]<min)
                min=s[row-1][i];
        }
        return min;
    }
}
