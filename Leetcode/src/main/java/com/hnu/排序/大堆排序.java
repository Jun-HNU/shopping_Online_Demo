package com.hnu.排序;

import java.util.Arrays;

public class 大堆排序 {

    public static void swap(int [] c, int i, int j)
    {

    int tem=c[i];
    c[i]=c[j];
    c[j]=tem;

    }
    public static void adjust(int [] c,int p,int end)
    {

        //单次调整内部是，叶子节点的遍历是自上而下。
        for (int i = 2*p+1; i <end ; i++) {
            int k=i;
            if(i+1<end)
            {

                if(c[i+1]>c[i])
                    k=i+1;
            }
            if(c[k]>c[p]) {
                swap(c, k, p);
                p=k;//将“调整节点”进行更新。
            }
            else
                ////由于调整节点是自减，遍历节点是自加的。
                //所以如果当前"调整节点”已经满足条件，后面的节点在之前的调整中已经满足堆的性质的。
                // 那么可以直接退出

                break;

            }

        }




   public static int [] heapSort(int [] c)
    {

        //创建初始堆，从最后一个非叶子子节点p开始调整p=c.length/2-1,定义p为”调整节点“，然后p递减
        // 继续将其他非叶子节点作为“调整节点”，直到最后一个“调整节点”是根节点，完成最后一轮调整。
        //创建堆时，”调整节点“的更新自下而上。”调整节点“通常是非叶子节点
        for (int p = c.length/2-1; p>=0; p--) {

            adjust(c,p,c.length);
        }


        //这里由于是排序，在已经是大堆结构后，堆顶的元素是最大的。
        // 只需要将堆顶元素与堆的最后一个节点元素进行交换，并缩小堆结构的边界范围，继续调整为堆结构
        //直到最终的堆结构只剩下一个元素，那么数组将变得有序了
        for (int i = c.length-1; i >=0 ; i--) {
            swap(c,i,0);
          //由于只是根节点被换了，只需要将根节点作为调整节点，完成一轮调整即可恢复堆结构。
            //并且是前数组的前i个节点进行堆的堆的调整
            adjust(c,0,i);

        }
        return c;

    }

    public static void main(String[] args) {

        int arr[] = { 4, 6, 8, 5, 9 };
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
