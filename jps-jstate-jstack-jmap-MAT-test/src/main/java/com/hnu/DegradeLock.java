package com.hnu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
//**锁降级是指在读写锁中，为了保证写后数据的一致性，将写锁降级为读锁。**其实就是利用了读写锁的互斥性。
public class DegradeLock {
    private int value;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void writeRead(){
        try{
            Thread.sleep(300);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.writeLock.lock();//1
        this.value++; //2
        //this.readLock.lock(); //3
        this.writeLock.unlock();//4

        System.out.printf("%s 读取到value的值为 %d \n",Thread.currentThread().getName(),this.value);
       // this.readLock.unlock();//5
    }

    public static void main(String[] args) {
        DegradeLock dl = new DegradeLock();
        new Thread(()->dl.writeRead()).start();
        new Thread(()->dl.writeRead()).start();
        new Thread(()->dl.writeRead()).start();
        new Thread(()->dl.writeRead()).start();
        new Thread(()->dl.writeRead()).start();
    }
}
