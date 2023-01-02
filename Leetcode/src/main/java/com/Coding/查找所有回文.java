package com.Coding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 查找所有回文 {

    public static void main(String[] args) {

        List pdsValue = getpds("Hello woworlrow");

    }

    public static List getpds(String s) {
        LinkedList list = new LinkedList();
        if(s == null || s.length() == 0) {
            return list;
        }

        for(int i = 0; i < s.length(); i++) {    //枚举字符串每个字符，从左到右。
            //以各点为中心，向左右散开求回文。
             String R1=countPalidrome(s, i, i); //确认中心点
            String R2= countPalidrome(s, i, i + 1); //考虑回文长度为偶数的情况，确认中心点为两个数
        if(R1.length()>1) {
            System.out.println(R1);
            list.add(R1);
        }
            if(R2.length()>1)
            {
                System.out.println(R2);
                list.add(R2);
            }
        }
        return list;
    }

    private static String countPalidrome(String s, int start, int end) {
        int count = 0;
        String rs= new String();
        if(start==end)
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;

            if(count==1)
                rs=String.valueOf(s.charAt(end));
            else
            {
                rs=String.valueOf(s.charAt(end))+rs+String.valueOf(s.charAt(end));
            }
            start--;
            end++;
        }
        else if(start!=end)
            while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
                count++;
                rs=String.valueOf(s.charAt(end))+rs+String.valueOf(s.charAt(end));
                start--;
                end++;
            }

        return rs;
    }









    public static List<String> getStringhw(String hwstring){
        List<String> list=new ArrayList<String>();
        char[] chars = hwstring.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //得到一级字符串
            String start= String.valueOf(chars[i]);
            for (int j = (i+2); j < chars.length; j++) {//二级从第3个开始查
                //判断后字符是否与一级字符相等
                String end = String.valueOf(chars[j]);
                if (start.equals(end)) {
                    int hw = (j - i) + 1;
                    if (hw >= 3 && hw % 2 == 1) {//当大于3时循环判断对应的字符串是否相等
                        boolean bon=true;
                        for (int k = 1; k < hw; k++) {
                            String start1 = String.valueOf(chars[i + k]);
                            String end1 = String.valueOf(chars[j - k]);
                            if (!start1.equals(end1)) {
                                bon=false;
                                break;
                            }
                        }
                        //组装回文字符串
                        if (bon) {
                            String value = "";
                            for (int k = i; k <= j; k++) {
                                value += String.valueOf(chars[k]);
                            }
                            list.add(value);
                        }
                    } else {
                        //不相等推出当前循环继续下次循环
                        continue;
                    }
                }
            }
        }
        return list;
    }



}
