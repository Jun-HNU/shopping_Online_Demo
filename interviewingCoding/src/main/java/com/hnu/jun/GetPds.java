package com.hnu.jun;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
查找所有回文串
 */
public class GetPds {
    public static List getPds(String s) {
        LinkedList list = new LinkedList();
        if (s == null || s.length() == 0) {
            return list;
        }
        //枚举字符串每个字符，从左到右。
        for (int i = 0; i < s.length(); i++) {
            //以各点为中心，向左右散开求回文。
            String r1 = countPalidrome(s, i, i);
            //考虑回文长度为偶数的情况，确认中心点为两个数
            String r2 = countPalidrome(s, i, i + 1);
            if (r1.length() > 1) {
                list.add(r1);
            }
            if (r2.length() > 1) {
                list.add(r2);
            }
        }
        return list;
    }
    private static String countPalidrome(String s, int start, int end) {
        int count = 0;
        String rs = new String();
        if (start == end) {
            while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
                count++;

                if (count == 1) {
                    rs = String.valueOf(s.charAt(end));
                } else {
                    rs = String.valueOf(s.charAt(end)) + rs + String.valueOf(s.charAt(end));
                }
                start--;
                end++;
            }
        } else if (start != end) {
            while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
                count++;
                rs = String.valueOf(s.charAt(end)) + rs + String.valueOf(s.charAt(end));
                start--;
                end++;
            }
        }
        return rs;
    }
}
