package com.hnu.动态规划;

public class 不同路径63 {




    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;




        for (int i = 0; i < m; i++) {

            for (int j = 0; j <n ; j++) {

                if(obstacleGrid[i][j]==1)
                {
                    obstacleGrid[i][j]=0;
                    continue;
                }

                if(i==0&&j==0)
                {
                    obstacleGrid[0][0]=1;
                    continue;
                }
                if(i<1)
                    obstacleGrid[i][j]=obstacleGrid[i][j-1];
                else
                if(j<1)
                    obstacleGrid[i][j]=obstacleGrid[i-1][j];
                else
                {

                    obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }


            }
        }
        return obstacleGrid[m-1][n-1];

    }


    public static int uniquePaths(int m, int n) {

        int [][] obstacleGrid= new int[m][n];


          obstacleGrid[0][0]=1;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j <n ; j++) {

               if(i==0&&j==0)
               {
                   obstacleGrid[0][0]=1;
                   continue;
               }
           if(i<1)
               obstacleGrid[i][j]=obstacleGrid[i][j-1];
           else
               if(j<1)
               obstacleGrid[i][j]=obstacleGrid[i-1][j];
           else
                obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];

            }
        }
        return obstacleGrid[m-1][n-1];

    }




    public static void main(String[] args) {

        System.out.println(uniquePaths(3, 7));  ;

    }
}
