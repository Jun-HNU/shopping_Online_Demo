package com.hnu.二分查找;
public class 搜索旋转排序数组33 {

    /*

    a b c d e

nums = [4,5,6,  7  ,0,1,2]

     */

//因为数组，从局部来看是有序，所以递归切分直到切分后的段是有序的，就可以使用二分查找。
// 将数组分治，分治后的区间
    int t;
    int [] n;
    public int help(int left,int right) {
        if(left>right) return -1;
        if (left==right) {
            if (n[left] == t) return left;
            return -1;
        }
        if (n[left] < n[right]) {
            if (t<n[left]||t>n[right]) return -1;
            int l=left;
            int r=right;
            while (l<= r) {//这里写等于号是可以，因为只有一个数时，还是可以跳出while循环的
                int M=l + (r - l) / 2;
                if(n[M]==t) return M;

                if(t>n[M])
                    l=M+1;
                else
                    r=M-1;
            }
            return -1;
        }
        else
        {
            //由于不满足n[left] < n[right]，所以继续切分数组
            int mid = left + (right - left) / 2;
            int m = n[mid];
            if (m == t) return mid;
            int a=  help(left,mid-1);//这里可能会导致有边界小于左边界，所以help()的参数需要进行判断==》if(left>right) return -1;
            int b=  help(mid+1,right);
            if(a!=-1) return a;//被搜索的数字只可能在一个区间
            if(b!=-1) return b;
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        t=target;
        n=nums;
        int s = help(0, n.length-1);
        return s;
    }
}
