package com.hnu.DesignPattern;

public class Test {
    public static void main(String[] args) {
        //new singleTon4.singleTonHold();
        System.out.println(singleTon5.INSTANCE.hashCode());
        //new singleTon4.singleTonHold();
        System.out.println(singleTon5.INSTANCE.hashCode());
        //System.out.println(new singleTon1()==new singleTon1());
        //System.out.println(new singleTon2()==new singleTon2());
        //System.out.println(singleTon1.getInstance()==singleTon1.getInstance());
        //System.out.println(singleTon1.getInstance()==singleTon1.getInstance());
    }
}
