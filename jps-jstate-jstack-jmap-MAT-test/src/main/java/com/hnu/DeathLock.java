package com.hnu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeathLock {

    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void deathLock() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    lock1.lock();//当前线程获得lock1这把锁。
                    System.out.println("t1 lock1"+Thread.currentThread().getName()+": " +System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lock();//当前线程获得lock1锁，睡了1秒后，醒来想继续获得lock2这把锁。但是由于在当前线程睡眠时，lock2这把锁已经被其他线程获得。当前线程阻塞。
                    System.out.println("t1 lock2"+Thread.currentThread().getName()+": " +System.currentTimeMillis());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock2.lock();//与上面的情况相似。
                    System.out.println("t2 lock2"+Thread.currentThread().getName()+": " +System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lock();
                    System.out.println("t2 lock1"+Thread.currentThread().getName()+": " +System.currentTimeMillis());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.setName("mythread1");
        t2.setName("mythread2");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        deathLock();
    }
}
//11720