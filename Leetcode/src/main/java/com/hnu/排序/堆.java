package com.hnu.排序;

import java.util.Arrays;

public class 堆 {


    public static int getPNode(int [] c,int l){

        int x=(l-1)/2;

        return c[x];
    }
    public static int getLNode(int [] c,int p){

        return c[2*p+1];
    }
    public static int getRNode(int [] c,int p){

        return c[2*p+2];
    }
//将数组作为一颗逻辑上的完全二叉树，调增为满足大堆的的二叉树

    public static void swap(int [] c,int i,int p)
    {
        int tmp=c[p];
        c[p]=c[i];
        c[i]=tmp;
    }

    public static void buildTree(int [] c,int h){

        int p=0;//父节点的角标，父节点在前，数组的每一个元素都是父节点。左节点的下角标为i,走节点的下标为i+1
        for (int i=2*p+1;i<h&&p<h;p++,i=2*p+1){
            if(i+1<h&& c[i]<c[i+1])//比较左右节点，如果左节点比右节点小，则让右节点与父节点交换，有也就是i=1;
            {
                i++;
            }
            //System.out.println(p);
            if(c[i]>=c[p])//如果叶节点比父节点大，则进行交换
            swap(c,i,p);
        }


    }

    public static void sortTree(int [] c)//将末尾的元素与堆顶的元素进行交换

    {
        buildTree(c,c.length);

        for (int i=c.length-1;i>0;i--){
            swap(c,0,i);
            buildTree(c,i);
        }

    }


    public static void main(String[] args) {
        int []  a={10,4,5,6,7};
        sortTree(a);
        System.out.println(Arrays.toString(a));

    }
}
