package com.hnu.jun;

import java.util.Map;
import java.util.TreeMap;

/*
最长公共子串
 */
public class GetLCS {
    public static TreeMap getLCS(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return null;
        }
        int l1 = s1.length();
        int l2 = s2.length();
        //int res = 0;
        TreeMap<Integer, StringBuilder> map = new TreeMap<>();
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                int m = i;
                int n = j;
                int len = 0;
                StringBuilder r = new StringBuilder();
                while (m < l1 && n < l2 && s1.charAt(m) == s2.charAt(n)) {
                    len++;
                    m++;
                    n++;
                    r.append(s1.charAt(m));
                }
                map.put(r.length(), r);
                //res = Math.max(res, len);
            }
        }
        return map;
    }
}
