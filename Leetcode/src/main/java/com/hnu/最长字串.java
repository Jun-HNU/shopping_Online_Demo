package com.hnu;

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

}
