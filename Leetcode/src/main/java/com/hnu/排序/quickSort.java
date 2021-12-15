package com.hnu.排序;

import java.lang.reflect.Array;
import java.util.Arrays;

public class quickSort {

//public static swap(int [] a, i,j)

  public static void quickSort(int [] a,int l,int h)//l 和 h 分别为下限和上限


   {
       if (l>=h)//何时跳出递归函数的条件。
           return;


       int right=h;//右指针开始的位置，将会向左移动
       int left=l;//左指针开始的位置，将会向左移动
       int index=left;//坑位选第一个元素，这个坑位上的元素将被覆盖。
       int p=a[index];//将坑位的数先记录下来，也就是基准值，后面再把这个数放到坑上。

       while(left<right)
       {

           while(left<right)
           {
               if(a[right]<=p)
               {

                   a[index]=a[right];//找到之后将这个比基准值小的数放到坑上
                   index=right;//更新坑的角标，等待一个比基准值大的数，覆盖当前坑位。

                   left++;//左指针从上一个坑位的下一个位置开始遍历。
                   break;

               }
               right--;//找到一个小于基准值之前，右指针一直向左移动
           }
           while(left<right)
           {
               if(a[left]>p)
               {
                   //int temp=a[index];
                   a[index]=a[left];
                   index=left;
                   right--;
                   break;

               }
               left++;
           }


       }

            a[index]=p;

       quickSort(a,l,index-1);
       quickSort(a,index+1,h);

   }
    public static void main(String[] args) {


       int []  a={10,4,5,6,7};
        quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
