package com.hnu.数组;

import java.util.*;

class 三数之和 {


    public static void main(String[] args) {
        int[] nums = new int [] {-1,0,1,2,-1,-4};

        threeSum(nums) ;
    }




    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);//排号序后，相同的数肯定相邻
        List<List<Integer>> ls = new ArrayList<>();
        if(nums.length<3 ) return ls;

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案

                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {//小于sum这个数，说明和参数太小，就将左指针右移动
                        while (l < r && nums[l] == nums[l + 1]) l++;   // 跳过重复值
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) r--;//大于sum这个数，说明和参数太大，就将右指针左移动
                        r--;
                    }
                }
            }
        }
        return ls;
    }































    public static List<List<Integer>> threeSumChaoshi(int[] nums) {

List a= new ArrayList<ArrayList<Integer>>();
        
        if(nums.length<3 ) return a;

        //1.去重，选出所有出现次数大于1 的数，从其他数中选一个与之相加。
        //2.记录所有数子。
Map<Integer,Integer> s=new HashMap<Integer,Integer>();
List<Integer> nums1=new ArrayList<Integer>();
List<Integer> nums2=new ArrayList<Integer>();
for ( int i=0;i< nums.length;i++)
{
    if(s.containsKey(nums[i]))
    {
        if(s.get(nums[i])==1)
        nums1.add(nums[i]);////所有出现过至少两次的数
        s.put(nums[i],s.get(nums[i])+1);

    }
    else
    {
        s.put(nums[i],1);
       nums2.add(nums[i]);//所有出现过的数
        //System.out.println(nums[i]);
    }


}

        for ( int i=0;i< nums2.size();i++)//所有出现过的数
        {
            System.out.println(nums2.get(i));
        }
        System.out.println("***");
            for ( int m=0;m< nums1.size();m++)//所有出现过至少两次的数
            {
                System.out.println(nums1.get(m));
            }





    
for ( int i=0;i< nums2.size();i++)//所有出现过的数
{

for ( int m=0;m< nums1.size();m++)//所有出现过至少两次的数
{


 int res;
    res = nums2.get(i)+nums1.get(m)+nums1.get(m);
    if(res==0)
         {
        
           List tem=   new ArrayList<Integer>();
         //  tem.add(i);
            //tem.add(j);
            //tem.add(k);
           tem.add(nums2.get(i));
            tem.add(nums1.get(m));
            tem.add(nums1.get(m));
            a.add( tem);
         }
}

    for ( int j=i+1;j< nums2.size();j++)
    {


        for ( int k=j+1;k< nums2.size();k++)
            {
                int res=nums2.get(i)+nums2.get(j)+nums2.get(k);
         if(res==0)
         {
        
         //  List tem=   new ArrayList<Integer>();
            int[] tem = new int[3];
             tem[0]=nums2.get(nums2.get(i));
             tem[1]=nums2.get(nums2.get(j));
             tem[2]=nums2.get(nums2.get(k));
          // tem.add(i);
         //   tem.add(j);
          //  tem.add(k);
           /*tem.add(nums2.get(i));
            tem.add(nums2.get(j));
            tem.add(nums2.get(k));*/

            a.add( tem);
         }


            }

    }

}
return a;
    }
}