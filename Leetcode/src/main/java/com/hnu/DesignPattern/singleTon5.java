package com.hnu.DesignPattern;


/*
枚举单例
不仅可以解决线程同步，还可以防止反序列化。
枚举型是没有构造函数的，无法被反序列化。
（防止反射创建对象）
 */
public enum singleTon5 {
    INSTANCE;
    void f()
    {
        System.out.println("it is a test");
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(singleTon5.INSTANCE.hashCode());
                }
            }).start();
        }
    }
}

