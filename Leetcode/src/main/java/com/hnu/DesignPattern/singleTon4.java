package com.hnu.DesignPattern;

/*
私有构造函数
私有静态内部类
私有静态常量。
进阶版的懒加载，只有在调用时才创建一个实例对象

JVM在加载class时，它只加载一次。
singleTon4 只会被加载一次
singleTonHold只会被加载一次。
也只有一个INCTANCE会被创建，是线程安全的。

 */

public class singleTon4 {//加载时，静态内部类不会被加载和初始化，
    private singleTon4(){}//构造函数为私有，所以其他类无法new这个类。只能通过getInstance()
    private static class singleTonHold{
        private static final singleTon4 INSTANCE=new singleTon4();
    }
    class singleTonHold2{
        final singleTon4 INSTANCE=new singleTon4();

    }
   /* public static singleTon4 getInstance()//只有getInstance()被调用时，才会去加载singleTonHold，初始化new singleTon4()。
    {
        //return singleTonHold.INSTANCE;
        //return new singleTonHold2().INSTANCE;

    }*/
    public static singleTon4 getInstance()//只有getInstance()被调用时，才会去加载singleTonHold，初始化new singleTon4()。
    {
        return singleTonHold.INSTANCE;
        //return new singleTonHold2().INSTANCE;

    }
    public static void main(String[] args) {

        for (int i = 0; i <1000 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(singleTon4.getInstance().hashCode());
                }
            }).start();
        }
    }
}
