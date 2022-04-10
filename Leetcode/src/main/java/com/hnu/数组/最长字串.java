package com.hnu.数组;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class 最长字串 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }


    public static int lengthOfLongestSubstring2(String s) {

        HashMap<Character,Integer> map= new HashMap<>();
        int maxLen=0;//最大长度
        int begin=0;//
        int throd=0;
        int i=0;
        for(; i<s.length();i++)
        {

            if(!map.containsKey(s.charAt(i)))
            {
                map.put(s.charAt(i),i);
            }
            else
            {
                int firstindex= map.get(s.charAt(i));
               throd= i-firstindex;
               if(firstindex>begin)
                maxLen=maxLen>throd?maxLen:throd;
                begin=firstindex+1;
                map.put(s.charAt(i),i);

            }

        }
        throd=i-begin;
        maxLen=maxLen>throd?maxLen:throd;
        return maxLen;

    }





    public static int lengthOfLongestSubstring(String s) {

        HashMap<Character,Integer> map= new HashMap<>();
        int maxLen=0;
        for(int i=0; i<s.length();i++)
        {

            int j=0;
            CharSequence tmp=s.subSequence(j,i);
            if(!map.containsKey(s.charAt(i)))
            {
                map.put(s.charAt(i),i);
            }
            else
            {

                maxLen=maxLen>map.size()?maxLen:map.size();
                int begin= map.get(s.charAt(i));
               /* for (Map.Entry entry:map.entrySet())
                {


                    if((Integer)entry.getValue()<=begin)
                    map.remove(entry.getKey());

                }*/
                Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();

                for (;iterator.hasNext();)
                {
                    Map.Entry<Character, Integer> entry = iterator.next();
                    if(entry.getValue()<=begin)
                        iterator.remove();
                }
                map.put(s.charAt(i),i);

            }
            maxLen=maxLen>map.size()?maxLen:map.size();
        }
        return maxLen;

    }

    public static int lengthOfLongestSubstring3(String s) {
        Map charIndex =new HashMap<Character,Integer>();
        int begin=0;//最新字串的开始下角标
        int maxLenght=0;//字串长度
        int i=0;
        for(;i<s.length();i++)
        {
            //字符不在charIndex 的map中，或者之前的字串中出现过。即，当前字符串在begin 开始的最新字串中没有出现过。
            if(charIndex.get(s.charAt(i))==null||(Integer)charIndex.get(s.charAt(i))<begin)//
            {
                charIndex.put(s.charAt(i),i);//记录字符和脚标
                if(i-begin+1>maxLenght) maxLenght=i-begin+1;//没有重复字符时，相减后加1，即为长度
            }
            else{

                if(i-begin>maxLenght) maxLenght=i-begin;//此时i和begin角标对应的字符相同。直接相减即为长度。
//maxStr.put(i-begin,s.substring(begin,i));
                begin = (Integer)charIndex.get(s.charAt(i))+1;//更新最新字串下角标开始的位置。
                charIndex.put(s.charAt(i),i);

            }
        }
        // if(i-begin>maxLenght)

        return maxLenght;


    }

}
