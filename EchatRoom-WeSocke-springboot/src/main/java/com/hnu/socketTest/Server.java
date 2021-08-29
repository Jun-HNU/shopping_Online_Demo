package com.hnu.socketTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            //在8888端口上监听，看是否有连接请求过来
            System.out.println("开启服务端");
            Socket accept = serverSocket.accept();
            //获取socket连接
            System.out.println("有连接过来"+accept);
            Thread thread1 = new Thread(){//使用多线程来进行收发，线程1用来接收消息
                @Override
                public void run() {
                    try {
                        InputStream inputStream = accept.getInputStream();//获取socket输入流
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        //把输入流封装在DataInputStream
                        while (true){
                            String msg = dataInputStream.readUTF();//使用readUTF读取字符串
                            System.out.println("监听客户端消息： "+msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread2 = new Thread(){//线程2用来发送消息
                @Override
                public void run() {
                    try {
                        OutputStream outputStream = accept.getOutputStream();
                        //打开输出流
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        //把输出流封装在DataOutputStream中
                        Scanner scanner = new Scanner(System.in);
                        while (true){
                            String strServer = scanner.next();
                            dataOutputStream.writeUTF(strServer);//使用writeUTF发送字符串
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread1.start();
            thread2.start();
            thread1.join();
            System.out.println("服务器关闭");
            accept.close();//关闭单个socket连接
            serverSocket.close(); //关闭这个socket 服务器
        } catch (Exception e) {
            System.out.println("断开连接");
        }
    }
}