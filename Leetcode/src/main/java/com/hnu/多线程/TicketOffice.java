package com.hnu.多线程;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*
class SellTicket implements Runnable{//售票系统
	private static int  ticketNum;//此次发行票的数量，为共享数据
	private static Object SingleTonInstanceFlag;
	private Object obj = new Object();//对象锁，操作同步代码块之前必须先拿到锁
	private SellTicket() {
	}

	private static final SellTicket INSTANCE=new SellTicket();//final变量只会在被加载时初始化，保证了单例。
	//售票系统SellTicket的唯一入口
	public synchronized static SellTicket getSingleTonInstance(int  ticketNum,Object obj)
	{
		if(SingleTonInstanceFlag==null)
		{
		SellTicket.setTicketNum(ticketNum);
		SellTicket.setSingleTonInstanceFlag(obj);
		}
		else
		{
			System.out.println("当前不支持在售卖过程中，增加票的数量");
		}
		return INSTANCE;
	}



	private static void setSingleTonInstanceFlag(Object singleTonInstanceFlag) {
		SingleTonInstanceFlag = singleTonInstanceFlag;
	}


	private static void setTicketNum(int ticketNum) {
		SellTicket.ticketNum = ticketNum;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (obj)//同步代码块
			{
				if (ticketNum>0) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"售出一张票，耗时100ms，票号为："+ticketNum--);
				}
				else
					{
					System.out.println("所有票已售罄,"+Thread.currentThread().getName()+"无票可售");
					break;
					}
			}
		}
	}
	
}
*/



class SellTicket implements Runnable {//售票系统,具备售卖功能

	private static   volatile int  ticketNum;//此次发行票的数量，为共享数据
	private static Object SingleTonticketNumFlag;//

	private Lock lock = new ReentrantLock();//只有当前lock的ower是当前线程才能操作同步代码块，否则排队
	private SellTicket() {
	}
	//售票系统只有一个
	private static final SellTicket INSTANCE=new SellTicket();//final变量只会在被加载时初始化，保证了单例。
	//售票系统SellTicket的唯一入口，并且线程安全
	public synchronized static SellTicket getSingleTonInstance(int  ticketNum,Object obj)
	{
		if(SingleTonticketNumFlag==null)
		{
			SellTicket.setTicketNum(ticketNum);
			SellTicket.setSingleTonticketNumFlag(obj);
		}
		else
		{
			System.out.println("当前不支持临时增加票的数量");
		}
		return INSTANCE;
	}


	private static void setSingleTonticketNumFlag(Object singleTonticketNumFlag) {
		SingleTonticketNumFlag = singleTonticketNumFlag;
	}

	private static void setTicketNum(int ticketNum) {
		SellTicket.ticketNum = ticketNum;
	}


	@Override
	public void run() {
		while (ticketNum > 0) {
			try {
				lock.lock();//加锁，同步代码块
				if (ticketNum > 0) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"售出一张票，耗时100ms，票号为："+ticketNum--);

				}
			} finally {
				// 释放锁
				lock.unlock();
			}
		}
		System.out.println("所有票已售罄,"+Thread.currentThread().getName()+"无票可售");
	}
}

public class TicketOffice {
	public static void main(String[] args) {
		SellTicket sellTicket = SellTicket.getSingleTonInstance(100,new Object());//此次发行票的数量为100
		//SellTicket.getSingleTonInstance(100,new Object());
		Thread window1 = new Thread(sellTicket,"1号窗口");//建一个售卖窗口，通过售卖系统售票，命名为1号窗口
		Thread window2 = new Thread(sellTicket,"2号窗口");
		Thread window3 = new Thread(sellTicket,"3号窗口");

		window1.start();//1号窗口开始售票
		window2.start();
		window3.start();
	}


}