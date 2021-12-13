package com.hnu;

public class SynchronizedDemo2 {

   static Object object = new Object();
    public void method1() {
        synchronized (object) {

        }
        method2();
    }

    private static void method2() {

    }
}
