package com.hnu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class myRejectSolution {


    BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);
    ThreadPoolExecutor service = new ThreadPoolExecutor(20, 20, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());

}
