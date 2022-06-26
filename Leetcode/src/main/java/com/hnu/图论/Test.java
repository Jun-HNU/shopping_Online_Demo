package com.hnu.图论;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {

    List<Integer>[] graph;
    

    public static void main(String[] args) {
        List p=new LinkedList<String>();
        List o=new LinkedList<String>(p);
        o.addAll(3,p);
        Object[] arrayObject = o.toArray();
        Object[] objects = o.toArray(arrayObject);
        int [] a= new int []{1,5,7};
        List<int[]> ints = Arrays.asList(a);

        Integer [] b= new Integer[] {1,5,7};

        List<Integer> integers = Arrays.asList(b);//无法add元素
        List<Integer> integers2  = new ArrayList<>(integers);
        Object[] objects2 = integers2.toArray();
        Integer[] b2= (Integer[])objects2;

        Object[] objects3 = new Object[1];
        Object c=new Object();
        List<Object> objects1 = Arrays.asList(c);
        String s = Arrays.toString(a);
    }
}
