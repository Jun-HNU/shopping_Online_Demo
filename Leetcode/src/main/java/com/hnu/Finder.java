package com.hnu;

import java.util.*;
 
public class Finder {


    public static void main(String[] args) {

       // int[] t = {1,7,3,45,3,8};

       // System.out.println(findKth(t,6,3));

        Scanner sc = new Scanner(System.in);

        int n= sc.nextInt();

        int[] num2 = new int[n];

        for(int i = 0; i < n; i ++) {
            num2[i] = sc.nextInt();
        }
        //int K=3;
        sc.close();

        Scanner sc2 = new Scanner(System.in);

        int K = sc2.nextInt();


        System.out.println(findKth(num2,n,K));

    }
    public static int findKth(int[] a, int n, int K) {
       quickSort(a,0,a.length-1);
       return a[n-1-K+1];
    }
    
   //快排
   public static void quickSort(int[] a,int low,int high){
 
        if(low > high){
            return;
        }
 
        int i = low;
        int j = high;
 
        int temp = a[low];
 
        while (i<j){
            while (i<j && temp <= a[j]){
                j--;
            }
 
            while (i<j && temp >= a[i]){
                i++;
            }
 
            if(i<j){
              int tmp = a[j];
              a[j] = a[i];
              a[i] = tmp;
            }
        }
 
        a[low] = a[j];
        a[i] = temp;
 
        quickSort(a,low,j-1);
        quickSort(a,j+1,high);
    }
}