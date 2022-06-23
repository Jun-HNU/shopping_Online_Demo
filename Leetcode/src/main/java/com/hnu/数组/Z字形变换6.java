package com.hnu.数组;

import java.util.LinkedList;
import java.util.List;

public class Z字形变换6 {

    /*

P   A   H   N
A P L S I I G
Y   I   R


PAY
 P
ALI
 S

     */
    public String convert(String s, int numRows) {

        //列数为n,
        // i+1 j-1
        //if j==0, ....



        List<char[]> ls= new LinkedList<>();
        int i=0;
        int j=0;
        int r=0;
        String result ="";

        while(r<s.length())
        {
            char [] temps= new char[numRows];
            for (int k = 0; k <numRows ; k++) {
                temps[k]=' ';
            }

            if(j==0)
            {

                for (; j < numRows&&r<s.length(); j++) {
                    char c = s.charAt(r);
                    r++;
                    temps[j]=c;
                }
                //
               // if(j>1)
                    j--;

            }
            else
            {
                char c = s.charAt(r);
                r++;
                temps[j]=c;
            }
            i++;
            j--;
            ls.add(temps);

        }

        for (int l = 0; l <numRows ; l++) {
            for (int k = 0; k <ls.size() ; k++) {

                char c = ls.get(k)[l];
                if(c!=' ')
                {
                    result=result+String.valueOf(c);
                }

            }


        }
        return result;

    }


}
