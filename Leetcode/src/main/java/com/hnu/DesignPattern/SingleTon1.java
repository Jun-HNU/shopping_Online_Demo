package com.hnu.DesignPattern;


/*
无论INTANCE用不用，INSTANCE在类加载时都会被创建。
所以称之为懒加载。
 */
public class SingleTon1 {
    private SingleTon1(){}//让构造函数私有，其他类没法new 这个类只能通过getInstance()方法
    private static final SingleTon1 INSTANCE=new SingleTon1();//final变量只会在被加载时初始化，保证了单例。
    public static SingleTon1 getInstance()
    {
        return INSTANCE;
    }
    public static void main(String[] args) {
System.out.println(new SingleTon1()==new SingleTon1());
        for (int i=0;i<100;i++
             ) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println(
                            SingleTon1.getInstance()
                           // SingleTon1.getInstance().hashCode()
                    );
                }
            }).start();


        }
            }




}
