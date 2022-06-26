package com.hnu.回溯;

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


    public static void main(String[] args) {
        new N皇后51().solveNQueens(4);
    }
}
