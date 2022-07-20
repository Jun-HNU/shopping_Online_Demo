package com.hnu.动态规划DP.填表;

public class 最长公共子序列 {
    /*
输入：text1 = "abcde",
text2 = "ace"
输出：3


无后效性 的最值问题
base case
状态转移方程
结束条件

  a b a d e l2
a 1 1 1 1
a 1 1 2 2
e 1 1 2

l1

s[i][j]=b[i][j]+max(s[i][j-1],s[i-1][j])
     */





    public int longestCommonSubsequence(String text1, String text2) {
        int l1=text1.length();
        int l2= text2.length();
        int [][] s= new int [l1][l2];
        int x=-1;
        int y=-1;
        for (int i=0; i < l1 ; i++) {
            for (int j=0; j < l2; j++) {
                int max=0;
                if(i!=0&&j!=0)
                    max=Integer.max(s[i][j-1],s[i-1][j]);
                else
                    if(j!=0) max=s[i][j-1];
                else
                    if(i!=0) max=s[i-1][j];
                s[i][j] =max;
                if(text1.charAt(i)==text2.charAt(j))
                {
                    if(!(i==x||j==y))
                    {
                        s[i][j]=1+s[i][j];
                        x=i;
                        y=j;
                    }

                }

            }
        }
return s[l1-1][l2-1];
    }

    public static void main(String[] args) {


        new 最长公共子序列().longestCommonSubsequence(
                "oxcpqrsvwf",
                "shmtulqrypy");
    }
}
