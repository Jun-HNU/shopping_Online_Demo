package com.hnu.排序;

import java.util.Arrays;
//https://blog.csdn.net/wenwenaier/article/details/121314974
//
public class 堆 {





    public static void main(String[] args) {
        int arr[] = { 4, 6, 8, 5, 9 };
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        //数组是从0开始的，从最后一个非叶子节点i开始调整。
        //并将i递减，从而创建大堆
// 构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
// 从第一非叶子结点开始调整
            changeHeap(arr, i, arr.length);
        }
// 调整堆结构和交换堆顶元素与末尾元素。 将堆元素移除，将堆的最后一个元素放到堆顶。再次建堆。
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);// 将堆顶元素与末尾元素进行交换
            changeHeap(arr, 0, j);// 重新对堆进行调整
        }
    }

    /*
     * 交换元素
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * 调整大顶堆
     */
    private static void changeHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // temp暂存当前元素
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {// 从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {// 如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {// 如果子节点大于父节点，将子节点值赋给父节点
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;// 将temp值放到最终的位置

    }
}