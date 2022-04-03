package com.hnu.数组;

import java.util.*;

public class 两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {

        if(nums1==null|| nums2==null) return null;

        Set<Integer> s= new HashSet<>();
        Set<Integer> s2= new HashSet<>();


        for (int i = 0; i <nums1.length ; i++) {
            s.add(nums1[i]);
        }

        List<Integer> l= new LinkedList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (s.contains(nums2[i]))
                s2.add(nums2[i]);
           /* if(!l.contains(nums2[i]))
                l.add(nums2[i]);*/
        }

        int [] r= new int [s2.size()];
        int i=0;
        for ( Integer it : s2) {
            r[i++]=it;

        }

return r;
        // int[] --> Integer[]
      /*  int[] arr = {1, 2, 3, 4, 5};
        Integer[] integers = Arrays.stream(arr).boxed().toArray(Integer[]::new);*/
// Integer[] --> int[]
      //  int[] ints = Arrays.stream(l.toArray(r)).mapToInt(Integer::valueOf).toArray();

        //return Arrays.stream(l.toArray(r)).mapToInt(Integer::valueOf).toArray();
    }
}
