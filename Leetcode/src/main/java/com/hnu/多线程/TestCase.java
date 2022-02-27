package com.hnu.多线程;

public class TestCase {
    public static void main(String[] args) {
            SellTicket sellTicket = SellTicket.getSingleTonInstance(100,new Object());//此次发行票的数量为100
            SellTicket.getSingleTonInstance(100,new Object());
            Thread window1 = new Thread(sellTicket,"1号窗口");//建一个售卖窗口，通过售卖系统售票，命名为1号窗口
            Thread window2 = new Thread(sellTicket,"2号窗口");
            Thread window3 = new Thread(sellTicket,"3号窗口");

            window1.start();//1号窗口开始售票
            window2.start();
            window3.start();
        }
}

