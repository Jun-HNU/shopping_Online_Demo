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


    public static String test04(String s1, String s2, String s3) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        return sb.toString();
    }
}
