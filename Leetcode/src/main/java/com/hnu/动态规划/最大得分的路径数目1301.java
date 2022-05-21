package com.hnu.动态规划;


import java.util.List;

/*
["E23",
 "2X2",
 "12S"]
 =>[7,1]

["E12",
 "1X1",
 "21S"]

 从地点出发，到任意点都有一个最大的得分，要求的的是终点。
 变化的变量是坐标i 和j,应当是目标数组的一个维度
 */
public class 最大得分的路径数目1301 {

    int [][] S;
    int [][] T;
    public int dfs(int endColum,int endRow,int [][] d)
    {

        T[endColum][endRow]=  Math.max(T[endColum-1][endRow],T[endColum][endRow-1])+S[endColum][endRow];

        return 0;
    }
    public int[] pathsWithMaxScore(List<String> board) {
        int n=board.size();
        int [][] S= new int [n][n];
        int [][] T= new int [n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n ; j++) {
                int t=board.get(i).charAt(j)-'0';
                if(t>0&&t<10)
                    S[i][j]=t;
                else
                    S[i][j]=-1;
            }

        }

        S[0][0]=0;S[n-1][n-1]=0;
        int x=-1;
        int y=-1;
        for (int i = n-1; i >=0; i--) {
            for (int j = n-1; j >=n ; j--) {

               // if(i!=n-1||j!=n-1) T[i][j]= Math.max( Math.max(T[i+1][j],T[i][j+1]),T[i][j+1])+S[i][j];
                if(i!=n-1||j!=n-1) {
                    if(S[i][j+1]==-1){
                        T[i][j]= Math.max(T[i+1][j],T[i+1][j+1])+S[i][j];
                    }
                    if(S[i+1][j]==-1){
                        T[i][j]= Math.max(T[i][j+1],T[i+1][j+1])+S[i][j];
                    }
                    else
                    T[i][j] = Math.max(T[i + 1][j], T[i][j + 1]) + S[i][j];
                }
                else if(j!=n-1)
                {
                    if(S[i][j+1]==-1){
                        if(i==n-1) T[i][j]=S[i][j];
                        else
                        T[i][j]= Math.max(T[i+1][j],T[i+1][j+1])+S[i][j];
                    }
                    if(i!=n-1)
                    if(S[i+1][j]==-1){
                        T[i][j]= Math.max(T[i][j+1],T[i+1][j+1])+S[i][j];
                    }
                    else

                    T[i][j]=  T[i][j+1]+S[i][j];
                }
                else if(i!=n-1)
                    T[i][j]=  T[i+1][j]+S[i][j];

            }

        }
        int [] r=new int [2];

        return r;

    }
}
