package com.hnu;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.ReentrantLock;


public class LockInterrupt extends Thread {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public LockInterrupt(int lock, String name) {

        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                lock1.lockInterruptibly();
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            //System.out.println(Thread.currentThread().getId() + ":线程退出");
            System.out.println(Thread.currentThread().getName() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockInterrupt t1 = new LockInterrupt(1, "LockInterrupt1");
        LockInterrupt t2 = new LockInterrupt(2, "LockInterrupt2");
        t1.start();
        t2.start();
        Thread.sleep(1000);

        DeadlockChecker.check();
    }

    static class DeadlockChecker {

        private final static ThreadMXBean mbean = ManagementFactory
            .getThreadMXBean();

        public static void check() {
            //1.创建一个线程去检测所有发生死锁的线程，后面将这个线程设置为守护线程。

            Thread tt = new Thread(() -> {
                {
                    // TODO Auto-generated method stub
                    while (true) {
                        //2.先找到发生死锁的所有线程ID，deadlockedThreadId
                        long[] deadlockedThreadIds = mbean.findDeadlockedThreads();//
                        if (deadlockedThreadIds != null) {
                            ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                            for (Thread t : Thread.getAllStackTraces().keySet()) {
                                for (int i = 0; i < threadInfos.length; i++) {
                                    //3.遍历所有的线程栈，根据id找到线程本身，对线程进行中断。
                                    if (t.getId() == threadInfos[i].getThreadId()) {
                                        System.out.println(t.getName());
                                        t.interrupt();
                                    }
                                }
                            }
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }

                }
            });
            tt.setDaemon(true);
            tt.start();
        }

    }

}
