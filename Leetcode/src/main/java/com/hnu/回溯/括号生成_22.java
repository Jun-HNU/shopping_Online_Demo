package com.hnu.回溯;


import java.util.LinkedList;
import java.util.List;

/*
()

---------
()()
(())
--------------
*****
()()()
x(()())
****

*****
x(())()
()(())
((()))
****
--------------
********
()()()()
(()()())

*******
(()())()
()(()())
((()()))
x********

(())()()
()(())()
((())())






输出
["(((())))","()((()))","((()))()","(()(()))","()()(())","()(())()","((())())","()(())()","(())()()","((()()))","()(()())","(()())()","(()()())","()()()()"]
预期结果
["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]




f(n-1)=T1+.....Tn-1

for each (1,n-1)

"("+Ti+")","()"+Ti,Ti+"()"

 */
class 括号生成_22 {

    public List<String> generateParenthesis(int n) {
    //求包含"(",")"的全排列
    //满足条件为：“(”个数不小于“)”,当左括号和右括号个数相等时记录结果集。

    //所以需要用变量记录，括号和有括号的数量。
    //结束递归的条件为，不合法的排列，或者当左括号和右括号个数相等
    String [] ls=new String[]{"(",")"};//记录选择列表,
    //由于列表元素很少，完全可以列举，所以列表不作为递归函数的参数

    String cur="";//存放当前的路径
    List<String> re= new LinkedList<>();//记录可行的路径结果集
    help2("",n,n,re);

            return re;
}
    void  help2(String cur,int left,int right,List<String> re) {
        if (left == 0 && right == 0)
        {re.add(cur);
            return;}
        if (left > right)
            return;//右括号比左括号多，非法括号，退出
        if (left<0||right<0)return;
        //做出选择，添加“(",左括号
        cur=cur + "(";
        left=left-1;
        help2(cur, left, right, re);
        if(cur.length()>0)
            cur = cur.substring(0, cur.length()-1);
        left++;//撤销选择

        //做出选择，选择右括号
        cur=cur + ")";
        right=right-1;
        help2(cur, left,right, re);

        if(cur.length()>0)
            cur = cur.substring(0, cur.length()-1);
        right++;//撤销选择

    }

    void  help(String cur,int left,int right,List<String> re) {
        if (left == 0 && right == 0)
        {re.add(cur);
            return;}
        if (left > right)
            return;//右括号比左括号多，非法括号，退出
        if (left<0||right<0)return;
        //做出选择，添加“(",左括号
//此处的--left，不能换成left--,否则函数函栈内部的left变量并没有减1的效果
        help(cur + "(", --left, right, re);
//在help函数栈内部，cur的值是在执行“+ "("”在之后的值，
//上一个help函数执行完成之后，cur的值是没有“+ "("”在之前的值，
        // if(cur.length()>0)
        // cur = cur.substring(0, cur.length()-1);
        left++;
        help(cur + ")", left, --right, re);
        // if(cur.length()>0)
        // cur = cur.substring(0, cur.length()-1);
        right++;

    }
}

