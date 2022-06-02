package com.hnu.动态规划;


/*

给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。

你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍 'X'。
在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。

一条路径的 「得分」 定义为：路径上所有数字的和。

请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，
请把结果对 10^9 + 7 取余。

如果没有任何路径可以到达终点，请返回 [0, 0] 。




board = [
"E23",
"2X2",
"12S"]
输出：[7,1]
[goal,count]
 */

import java.util.LinkedList;
import java.util.List;

public class 最大得分的路径数目_1301 {

    static int  mod=1000000007;
    public static int[] pathsWithMaxScore(List<String> board) {

        int[] r= new int [2];
        int len=board.size();
        int [][] goal=new int[len][len];//存放每一个状态的最大的得分
        int [][] count=new int[len][len];//存放有最大得分时的方案数
        int [][]  s=new int[len][len];
        for (int i = len-1; i >=0 ; i--) {//自下而上移动
            for (int j = len - 1; j >= 0; j--) {

                if (board.get(i).charAt(j) - '0' > 9)
                    s[i][j] = Integer.MIN_VALUE;
                else
                    s[i][j] = board.get(i).charAt(j) - '0';

                if((i==0&&j==0)||(i==len-1&&j==len-1)) s[i][j]=0;

            }
        }
            for (int i = len-1; i >=0 ; i--) {//自下而上移动
                for (int j = len-1; j >=0 ; j--) {


                int curMaxGoal=Integer.MIN_VALUE;
                int curmaxCount=Integer.MIN_VALUE;
                //三种不同方式到达[i，j]
                //向上

                if(i+1<len)//i+1,j
                {

                    if (s[i][j] > -1 && goal[i + 1][j] > -1) {
                        int curGoal = s[i][j] + goal[i + 1][j];


                        if (curGoal > curMaxGoal) {
                            curMaxGoal = curGoal;
                            curmaxCount = count[i + 1][j];
                        } else if (curGoal == curMaxGoal && curGoal != Integer.MIN_VALUE)
                            curmaxCount = curmaxCount + count[i + 1][j];
                        curmaxCount = curmaxCount % mod;
                        curMaxGoal = curMaxGoal % mod;
                    }
                }


                // 向左

                if(j+1<len)//i,j+1
                {
                    if(s[i][j]>-1&&goal[i][j+1]>-1) {
                        int curGoal = s[i][j] + goal[i][j + 1];
                        if (curGoal > curMaxGoal) {
                            curMaxGoal = curGoal;
                            curmaxCount = count[i][j + 1];
                        } else if (curGoal == curMaxGoal)
                            curmaxCount = curmaxCount + count[i][j + 1];
                        curmaxCount = curmaxCount % mod;
                        curMaxGoal = curMaxGoal % mod;
                    }

                }


                // 左上方
                if((i+1<len)&&(j+1<len))//i+1,j+1
                {
                    if(s[i][j]>-1&&goal[i+1][j+1]>-1) {
                    int curGoal = s[i][j] + goal[i+1][j+1];
                    if(curGoal>curMaxGoal)
                    {
                        curMaxGoal=curGoal;
                        curmaxCount=count[i+1][j+1];
                    }
                    else if(curGoal==curMaxGoal)
                        curmaxCount=curmaxCount+count[i+1][j+1];
                    curmaxCount=curmaxCount%mod;
                    curMaxGoal=curMaxGoal%mod;
                    }
                }

                goal[i][j]= curMaxGoal;
                count[i][j]=curmaxCount;
                    if((i==len-1&&j==len-1))
                    {
                        count[i][j]=1;
                        goal[i][j]=0;
                    }
            }


            
        }

        r[0]=goal[0][0]<0?0:goal[0][0];
        r[1]=count[0][0]<0?0:count[0][0];
        return r;

    }

    public static void main(String[] args) {
        String [] x=new String []{"E23","2X2","12S"};
        List<String> board = new LinkedList<>();
        /*board.add("E11");
        board.add("XXX");
        board.add("11S");*/

        /*board.add("E12");
        board.add("1X1");
        board.add("21S");*/
        /*board.add("E23");
        board.add("2X2");
        board.add("12S");*/
        board.add("E812");
        board.add("0812");
        board.add("4522");
        board.add("422S");
        //["E11","XXX","11S"]
        int[] ints = pathsWithMaxScore(board);
        System.out.println(ints[0]+" "+ints[1]);
        //System.out.println(Integer.MIN_VALUE%mod);



    }
}
