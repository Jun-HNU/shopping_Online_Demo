package com.hnu.链表;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache2 {

    int size;
    Map<Integer,Integer> map=new LinkedHashMap<>();

   LRUCache2(int size)
    {
        this.size=size;
    }


   public int get(int key)
   {
       if(!map.containsKey(key)) return -1;
       makeKeyRecently(key);
       return map.get(key);
   }

   public void makeKeyRecently(int key)
   {
       Integer value = map.get(key);
       //从链表的头部删除，插入尾部，尾部元素才是最近未使用的元素
       map.remove(key);
       map.put(key,value);
   }
   public void put(int key,int value)
   {

       if(map.containsKey(key))
       {

           map.put(key,value);
           makeKeyRecently(key);

       }
       else
       {
           if (size==map.size())
           {
               //删除头部的元素，头部元素为最近未使用的元素
               Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();
               map.remove(entry.getKey());
           }

           map.put(key,value);
       }


   }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */