package com.hnu.动态规划DP;

public class 出界的路径数576 {

    /*
    有题目知道，最大的移动次数maxMove是一个逐渐递减的值，所以创建数组时将其作为一个数组维度。
    也就是一个状态。
    而对于m*n的方格，是所有可能的状态，因此可以设计一个三维数组来记录状态。

题目求的是指定位置的开始，出界的路径数，显然在计算时可以从地点开始计算，只是调用递归时指定位置。
设计dfs(int k, int Row, int Column)求剩余步数为k时，当前坐标为Row和 Column时，出界的路径总数
如果某个点不是边界则有
s[i][j][k]=s[i+1][j][k-1]+s[i-1][j][k-1]+s[i][j-1][k-1]+s[i][j+1][k-1]
     */
    int mod = 1000000007;

    int [][][] S;
    int M=0;
    int N=0;
    // count = count % mod;
    //k表示还剩的最大的步数
    int [][] p=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int dfs(int k, int Row, int Column)
    {
        //base case，只要满足条件时，立即返回1
        if(Row<0||Row>=M||Column<0||Column>=N) return 1;
        //步数用尽时立即返回为0.
        if (k==0) return 0;
        //之前计算过，直接返回
    if(S[Row][Column][k]!=-1 ) return S[Row][Column][k];
    int s=0;
        for (int i = 0; i < 4; i++) {//四种行走的状态的枚举
           int r =Row+ p[i][0];
           int c=Column+ p[i][1] ;
            s=s+dfs(k-1,r,c);
            s=s%mod;
        }
        S[Row][Column][k]=s;
      return S[Row][Column][k];

    }
    /*

一个二维数组存放元数据，对于要求的结果同种类型的数据，
也用一个相同的size二维数组来存放，要求的结果，往往就在这个二维数组中。
    典型的dfs栈帧
    当前for 循环内部，原始坐标的变换i,j--> p[i][j]
    当前dfs栈帧,零时变量s,或者双重循环的第一层循环含零时变量
    当前dfs栈帧,零时变量s对应二维或者数组S[Row][Column],或者双重循环的第一层循环含二维数组。

     */

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {

        M=m;
        N=n;
        S= new int[m][n][maxMove+1];
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <maxMove+1 ; k++) {
                    S[i][j][k]=-1;

                }

            }

        }
       return dfs(maxMove, startRow,startColumn);
        }

    public static void main(String[] args) {
       // m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
        int paths = new 出界的路径数576().findPaths(2, 2, 2, 0, 0);
        System.out.println(paths);
    }




    }
