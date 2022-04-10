package com.hnu.数组;

public class 整数反转 {

    public int reverse(int x) {
        //Math.min(a[i],a[j]);
        int throd=1;
        for(int i=0;i<31;i++)
        {
            throd=throd*2;//计算出4字节INT 表示的最小数
        }
        /*
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(throd);
        */
        int z=Integer.MAX_VALUE%10;
        int f=Integer.MIN_VALUE%10;
        if(x==0) return 0;

        int c=x;
        int r=0;
        int flag=0;//找到第一个非0


        while(true)
        {
            int l=c%10;
            c=c/10;

            if(l!=0)
            {
                flag++;//找到第一个非0的，此处为标志
            }


            if(flag>0)//找到第一个非0数，开始计算
            {


                if(x>0)
                {

                    if(r>-1*(throd+1)/10) return 0;//当一个数在扩大10倍后可能越界之前进行判断
                    if(r==-1*(throd+1)/10&&l>z) return 0;//当一个数在扩大10倍后，再加上最后一位数可能越界之前进行判断

                }
                else
                {

                    if(r<throd/10) return 0;
                    if(r==throd/10&&l<f) return 0;
                }
                //System.out.println(l);
                r=r*10+l;
                if(c==0) break;//为0说明时最后一个数字，退出
            }
        }
        return r;
    }
}

