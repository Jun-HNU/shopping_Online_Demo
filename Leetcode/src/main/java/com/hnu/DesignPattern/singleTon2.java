package com.hnu.DesignPattern;
/*
要用的时候才加载。但是需要用到synchronized.
锁住的代码过多影响执行效率
 */
public class singleTon2 {
    private singleTon2(){}
    private static volatile singleTon2 INCTANCE;
    public synchronized static singleTon2 getInstance(){
    //public static singTon2 getInstance()  {
        if(INCTANCE==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INCTANCE=new singleTon2();
        }
        return INCTANCE;
    }
    public static void main(String[] args) {


        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(singleTon2.getInstance().hashCode());
                }
            }).start();
        }
        System.out.println();

    }
}
