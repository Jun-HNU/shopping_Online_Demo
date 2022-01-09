package com.hnu;

public class 两个正序数组中位数 {
    //时间复杂度o(log2(m+n)),空间复杂度o(1),一般执行二分查找法时间复杂度为log2(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //将寻找中位数的计算巧妙地转化为寻找第K小的数
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        //如果是奇数，则只需寻找第[（m+n）/2] + 1小的数
        if (totalLength % 2 == 1) {
            double median = findKthElement(nums1, nums2, (totalLength / 2) + 1);
            return median;
        } else {
            //如果是偶数，需要找[（m+n）/2]和[（m+n）/2] + 1小的数
            double median = (findKthElement(nums1, nums2, (totalLength / 2)) + findKthElement(nums1, nums2, (totalLength / 2) + 1)) / 2.0;
            return median;
        }
    }
    /**
     * 寻找第K小的数
     * @param nums1
     * @param nums2
     * @param k   所要寻找的第K小的数的位置
     * @return
     */
    public int findKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int length1 = nums1.length, length2 = nums2.length;
        //两个数组当前索引位置,要注意和后边两个数组比较索引位置不同。
        int index1 = 0, index2 = 0;

        while (true) {
            //异常情况2
            //如果数组为空（原本为空或者删除元素后为空)
            //注意判断条件是index
            if (index1 == length1) {
                //返回该数组中第K小数字
                return nums2[index2 + k - 1];
            }

            if (index2 == length2) {
                //返回该数组中第K小数字
                return nums1[index1 + k -1];
            }

            //情况3，k=1寻找俩数组中第一小的数，说明俩数组长度为1了
            if (k == 1) {
                //注意为index
                return Math.min(nums1[index1], nums2[index2]);
            }

            //正常条件下
            int half = k / 2;
        /*
        更新比较位置(以index为起点，正常情况更新寻找下标为index+（k/2) -1)
        出现特殊情况一：如果更新的比较位置越界，则比较位置为数组最后一个元素
        正常情况下位置更新为index + half -1
         */
            int newIndex1 = Math.min(index1 + half, nums1.length) - 1;
            int newIndex2 = Math.min(index2 + half, nums2.length) - 1;
            //比较位置的比较值
            int number1 = nums1[newIndex1], number2 = nums2[newIndex2];
            //如果数组1比较位置小于数组2比较位置，更新K，以及index
            if (number1 <= number2) {
                //把number1之前，包括number删除后，相当于寻找k = k - (newIndex1 - index1 + 1)小的数                ;
                k = k - (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k = k - (newIndex2 -index2 + 1);
                index2 = newIndex2 + 1;
            }
        }

    }



}