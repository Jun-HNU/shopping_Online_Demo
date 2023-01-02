package com.Coding;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class 最长公共子串 {

    public static void main(String[] args) {
        TreeMap map = getLCS("abcdefabcd", "abefabghi");
        StringBuilder LCSvalue = (StringBuilder)map.lastEntry().getValue();
        System.out.println(LCSvalue.toString());
    }
    public static TreeMap getLCS(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return null;
        }
        int l1 = s1.length();
        int l2 = s2.length();
        //int res = 0;
        TreeMap<Object, Object> map = new TreeMap<>();
        Map<Integer,StringBuilder> objectObjectHashMap;
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                int m = i;
                int n = j;
                int len = 0;
                StringBuilder r= new StringBuilder();
                while (m < l1 && n < l2 && s1.charAt(m) == s2.charAt(n)) {
                    len++;
                    m++;
                    n++;
                    r.append(s1.charAt(m));
                }
                map.put(r.length(),r);
                //res = Math.max(res, len);
            }
        }


        return map;
    }

}
