package com.hnu;

public class 最长回文子串 {


    public static void main(String[] args) {

        System.out.println(longestPalindrome("babad"));;

    }

    public static String longestPalindrome(String s) {

        int max = 0;
        String res = "";
        if( s.length()==1) return s;

        for (int i = 0; i < s.length(); i++) {


            int j = 0;
            while (
                    ((i - j >= 0) && (i + j +1< s.length()))
                            && (s.charAt(i - j) == s.charAt(i + j + 1))
            ) {
                j++;
                // System.out.println(i+" "+j);
            }


            if (max < 2 * (j)) {
                max = 2 * j;
                // System.out.println(i+" "+j);
                res = s.substring(i - j+1, i + j+1);

            }


            if (i > 0) {


                j = 1;
                while (
                        ((i - j >= 0) && (i + j < s.length()))
                                && (s.charAt(i - j) == s.charAt(i + j))
                ) {
                    j++;

                }


                if (max < 2 * (j - 1) + 1) {
                    max = 2 * (j - 1) + 1;
                    // System.out.println(i+" "+j);
                    res = s.substring(i - j+1, i + j);

                }
                // map.put()


            }

        }

        return res;
    }

}
