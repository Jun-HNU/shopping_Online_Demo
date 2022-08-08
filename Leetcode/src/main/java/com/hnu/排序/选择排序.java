package com.hnu.排序;

public class 选择排序 {

/*

4
6
8
0
1

/
 */

    void swapMistake(int a, int b) {

        int t = a;
        a = b;
        b = t;


    }
    void swap(int [] s,int a, int b) {

        int t = s[a];
        s[a]=  s[b];
        s[b] = t;


    }

    public int[] sortArray(int[] s) {
        {


            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length - i - 1; j++) {
                    if (s[j] > s[j + 1])
                        swapMistake(s[j], s[j + 1]);
                    swap(s,j, j + 1);

                }

            }

        }
        return s;

    }
}
