package com.hnu.动态规划;

import java.util.LinkedList;
import java.util.List;

public class 最小路径和64 {


    public int minPathSum(int[][] grid) {


        int m=grid.length;
        int n=grid[0].length;


        int[][] s = new int[m][n];
           s[0][0]=grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if(i>0&&j>0)
                s[i][j]=grid[i][j]+Math.min(s[i][j-1],s[i-1][j]);

               else if(i>0)
               {
                   s[i][j]=grid[i][j]+s[i-1][j];
               }
               else if(j>0)
                {
                    s[i][j]=grid[i][j]+s[i][j-1];
                }

            }

        }
        return s[m-1][n-1];

    }







}
