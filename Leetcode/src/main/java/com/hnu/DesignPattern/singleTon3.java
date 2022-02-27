package com.hnu.DesignPattern;

public class singleTon3 {
    private singleTon3(){}
    private static volatile singleTon3 INSTANCE;//volatile 本地优化，语句重拍。
    public static singleTon3 getInstance()
    {
        if (INSTANCE==null)//第一次判断，是为了避免，if内部代码被反复执行，被反复上锁。
            synchronized (singleTon3.class) {
                if (INSTANCE == null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE=new singleTon3();//将锁细粒度化。锁住的代码更少，双重判断。
                }

            }
         return       INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(singleTon3.getInstance().hashCode());
                }
            }).start();
        }
    }
}
