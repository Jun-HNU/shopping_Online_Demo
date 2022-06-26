package com.hnu.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class N皇后51 {
/*
1 2 6
3 4 8
 */
/*
按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
列举所有可能
String [] choseLs= new String []{"Q","."};
记录走完N个节点的路径
 List<String> curLs= new LinkedList<>();
记录当前走过的路径
 */


    /***
     * 题目是求满足条件的N皇后的结果集，显然对于这种问题可以用递归回溯（深度优先搜索+剪枝），
     * 求所有可行的结果集
     *
      * @param cur 当前字符串
     * @param s 选择要添加的字符
     * @param n 当前字符位于第n列
     * @param k 当前字符位于第k行
     * @param curLs 存放当前方案，里面存放了字符串，每个串包含N 个字符.
     *
     *             明确初始条件，明确选择列表
     */
/*
public void dfs(String cur,String s,int n,int k,List<String> curLs)
{
      boolean f=true;
    // 同一行n,*
    // 同一列 *,n
    // 同一斜行k-1,n-1,k-1,n+1
    //
    if(s.equals("Q")&&curLs.size() >0) {
        if(f==true)
        for (int i = curLs.size() - 1; i > 0; i--) {
            f= curLs.get(i).charAt(n) == 'Q'&&f;
        }
        if(f==true&&k>0&&n>0)
        for (int i = k-1, j = n - 1; i >= 0 && j >= 0; i--, j--) {

           f = curLs.get(i).charAt(j) == 'Q'&&f;
        }
        if(f==true&&k>0&&n<N-1)
        for (int i = k - 1, j = n + 1; i >= 0 && j < N; i--, j++) {

           f = curLs.get(i).charAt(j) == 'Q'&&f;
        }
    }

    if(f==false)
        return;
    else
        cur=cur+s;
    if(s.equals("Q")||s.equals("."))
        n=n+1;//下一个元素为第n列


    if(cur.length()==N)//当前字符串包含N个字符
    {

        if(!cur.contains("Q")) return;

        curLs.add(cur);//向当前方案添加当前行
        //return;
        cur="";//重置为空，继续需寻找当前方案的下一个字符串
        n=0;//重置为第0列
        k++;//下一行
        if(curLs.size()==N)//如果包含N 个字符串，则添加结果，
            // 说可见如果当前方案始终没有寻找到N个字符串则不会被添加到结果集中，
            // 会在在判断是否N皇后条件的地方，提前退出
        {
            res.add(curLs);
            //当前方案已经有N个字符串，可以推断后续不会再有可行的字符串了，直接添加当前方案到结果集中
           //继续需寻找下一个可行的方案，新建一个数组来存储该方案
            //curLs= new LinkedList<String>();
            return;
        }
    }
    //if(!cur.contains("Q"))
    dfs(cur,"Q",n,k,curLs);
    dfs(cur,".",n,k,curLs);
}

int N=0;
List<List<String>> res= new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
         N=n;
        dfs("","",0,0, new LinkedList<String>());
        return res;
    }
*/


    /*
    首选本题寻找所有可行集,会想到用递归回溯,
    回溯算法要明确三点:
    1,选择列表
    2.记录走过的路径
    3.结束递归的条件.


    对于本题,由于多数有'Q'和'.'组成,并且多数为'.',可以先初始化节点全部为'.',
    对于本题可以将每个节点都初始化为nxn的棋盘,而递归遍历下一个节点时可以理解为递归遍历棋盘的
    下一行(对应一个函数栈帧),对于当前行的每个节点,做出选择时,是对当前行的每一个元素进行选择,选择为'Q',还是撤销选择恢复为'.'.
    而递归结束的标志就是,递归到了当前


    一般的题目寻找所有可行的集合,在递归遍历树时,


     */




   List<List<String>> res = new LinkedList<>();
    int N;
    public List<String> fun(char [][] t)
    {
        List<String> ls=new ArrayList<>();
        for (int i = 0; i <t.length ; i++) {
            String s="";
            for (int j = 0; j <t.length ; j++) {
                s = Character.toString(t[i][j])+s;
            }
            ls.add(s);
        }
        return ls;
    }

    boolean isValid(char [][] tempChars,int n,int col)
    {
        //检测当前行是否以斤已经有'Q'
        for (int i = 0; i <=n; i++) {
            if(tempChars[i][col] == 'Q') return false;
        }
        //n-1行,row -1列
        for (int i = n-1,j=col-1; i >=0&&j>=0; i--,j--) {
            if(tempChars[i][j] == 'Q') return false;
        }
        //n-1行,row +1列
        for (int i = n-1,j=col+1; i >=0&&j<N; i--,j++) {
            if(tempChars[i][j] == 'Q') return false;
        }
        return true;

    }

    public List<List<String>> solveNQueens(int n) {
       /* char[] chars = new char[n];
        Arrays.fill(chars,'.');
        //String s = Arrays.toString(chars);
        //String[] lString= new String [n];
        char [][] tempChars= new char[n][n];
        Arrays.fill(tempChars,chars);*/
        char [][] tempChars= new char[n][n];
        for (int i = 0; i <tempChars.length ; i++) {
            for (int j = 0; j <tempChars[i].length ; j++) {
                tempChars[i][j]='.';
            }
        }
         N=n;
        //回溯一般会将记录当前走过的路径作为DFS函数的一个参数,这里记录递归到棋盘的第n行
        //并且与走过路径相对应的,会用一个变量表示进入下一个节点,(这里的节点可能是包含一个list,list中每个元素都要遍历做选择)
       //结束条件即为,当前递归完最后一行
       dfs( tempChars,0);
        return res;
    }

    /**
     *
     * @param tempChars
     * @param n 递归到当前第n行
     */
    public void dfs(char [][] tempChars,int n){
        if(n==N)//
        {
            res.add(fun(tempChars));
            return;
        }


        for (int i = 0; i < N; i++) {//对当前行的第i列元素进行选择

            if(!isValid(tempChars,n,i))
                //提前判断在当前方案的第n行第i列位置放'Q'是否是有效的(n,i)
                //如果不满足N皇后,则直接略过
                continue;

            tempChars[n][i]='Q';//做出选择
            //这里每一行最多只能放入一个Q,所以对于当前行的当前列选择'Q'时,应该直接进入下一个一行
            //n+1表示到达下一节点
            dfs(tempChars,n+1);
            tempChars[n][i]='.';
        }


    }
    public static void main(String[] args) {
       new N皇后51().solveNQueens(4);
        System.out.println("");
    }
}
