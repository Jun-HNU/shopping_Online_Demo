package com.hnu;

//import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: dev
 * @description:
 * @author: Mr.EternityZhang
 * @create: 2019-07-08 17:41
 */
//@Slf4j
public class TestThread {


    static class ThreadFactoryCustom implements ThreadFactory{
        private final AtomicInteger threadNum=new AtomicInteger(1);
        private final String namePrefix;

        private ThreadFactoryCustom(String namePrefix){
            this.namePrefix=namePrefix+"-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t=new Thread(r,namePrefix+threadNum.getAndIncrement());
            if(t.isDaemon()){
                t.setDaemon(true);
            }
            if(t.getPriority()!=Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    static class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

        private final String threadName;

        private final URL url;

        public AbortPolicyWithReport(String threadName, URL url) {
            this.threadName = threadName;
            this.url = url;
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            String msg = String.format("Provider端线程池满!" +
                            " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," +
                            " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)" ,
                    threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                    e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
            //log.warn(msg);
            if (!e.isShutdown()) {
                try {
                    //log.info("start get queue");
                    e.getQueue().put(r);
                    //log.info("end get queue");
                } catch (InterruptedException ee) {
                    //log.error(ee.toString(), ee);
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    public static ThreadFactory getThreadFactoryCustom(String name){
        return new ThreadFactoryCustom(name);
    }

    public static void main(String[] args) {
        String poolName="eternity";
        ThreadFactory factory=getThreadFactoryCustom(poolName);
        //log.info("核数={}",Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor executor=
                new ThreadPoolExecutor(100,400,5,
                        TimeUnit.SECONDS,new LinkedBlockingDeque<>(400),factory,new AbortPolicyWithReport(poolName,null));
        Long begin=System.currentTimeMillis();
        CountDownLatch count=new CountDownLatch(2000);
        AtomicInteger integer=new AtomicInteger(1);
        for(int i=0;i<2000;i++){
            executor.execute(()->{
                try {
                    //log.info("当前线程为={},数值={}",Thread.currentThread().getName(),integer);
                    integer.getAndIncrement();
                    Thread.sleep(500);
                    count.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            count.await();
            //log.info("阻塞数值={}",count.getCount());
            //log.info("活跃数量={}",executor.getActiveCount());
            if(executor.getActiveCount()==0){
                executor.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //log.info("耗时={}------结果={}",System.currentTimeMillis()-begin,integer);
    }
}