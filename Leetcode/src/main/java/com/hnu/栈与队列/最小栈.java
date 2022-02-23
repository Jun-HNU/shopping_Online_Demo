package com.hnu.栈与队列;


/*
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的类。

实现 MinStack 类:

    MinStack() 初始化堆栈对象。
    void push(int val) 将元素val推入堆栈。
    void pop() 删除堆栈顶部的元素。
    int top() 获取堆栈顶部的元素。
    int getMin() 获取堆栈中的最小元素。
    需要两个栈，一个栈保存数据，另一个记录压入比当前栈顶还要校的值

    3 2 1
    3 4 2 5 1

 */


import java.util.Stack;

public class 最小栈 {
    private Stack<Integer> r = new Stack<>();
    private Stack<Integer> min =new Stack<>();;


    public void push(int x)
    {
        if(r.isEmpty()||x<=min.peek())
        {
            r.push(x);
            min.push(x);
        }
        else
        {
            r.push(x);
        }
    }
    public void pop()
    {
        int y = r.pop();
        if(y==min.peek())
        {
            min.pop();
        }

    }
    public int top(){
        return r.peek();
    }

    public int getMin()
    {
        return min.peek();
    }
}