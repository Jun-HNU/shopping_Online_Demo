package com.hnu.二维数组;

import java.util.Arrays;
import java.util.Scanner;

public class DoubleArraysSort {
 
    public static int[] sort(int[] one, int[] two){
        int onesize = one.length;
        int twosize = two.length;
        int threesize = onesize + twosize;
        int[] three = new int[threesize];
        int i = 0;
        int j = 0;
        for(int t = 0; t < threesize; t++){
            if(i >= onesize){   //如果第一个数组比较完了，直接把第二个数组后面的数，排序到后面
                three[t] = two[j++];
            }else if(j >= twosize){  //如果第二个数组比较完了，直接把第一个数组后面的数，排序到后面
                three[t] = one[i++];
            }else{
                if(one[i] <= two[j]){
                    three[t] = one[i++];
                }else {
                    three[t] = two[j++];
                }
            }
        }
        return three;
    }


   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine().toString();//用nextLine（）可以读取一整行，包括了空格，next（）却不能读取空格
        String arr[] = str.split(",");//拆分字符串成字符串数组
        int a[] = new int[arr.length];//数组长度
        for(int j = 0; j < a.length; j++)
        {
            a[j] = Integer.parseInt(arr[j]);
            //System.out.print(a[j] + " ");
        }

        Scanner sc2 = new Scanner(System.in);

        String str2 = sc2.nextLine().toString();//用nextLine（）可以读取一整行，包括了空格，next（）却不能读取空格
        String arr2[] = str2.split(",");//拆分字符串成字符串数组
        int a2[] = new int[arr2.length];//数组长度
        for(int j = 0; j < a2.length; j++)
        {
            a2[j] = Integer.parseInt(arr2[j]);
            //System.out.print(a2[j] + " ");
        }

        int[] th = sort(a,a2);

       for(int j = 0; j < th.length; j++)
       {
           //th[j] = Integer.parseInt(th[j]);
           System.out.print(th[j] + ",");
       }

    }



    /*public static void main(String[] args){
        int[] o = {1,3,5,7,9,11,12};
        int[] t = {2,4,6,8,10};
        int[] th = sort(o,t);
        //System.out.println(Arrays.toString(th));

        //System.out.println(th);

        for(int j = 0; j < th.length; j++)
        {
            //th[j] = Integer.parseInt(th[j]);
            System.out.print(th[j] + ",");
        }
    }
*/

}