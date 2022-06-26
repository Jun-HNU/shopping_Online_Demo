package com.hnu.动态规划DP;

public class 最小路径和的轨迹64进阶 {



    private int size;


    /*
    记录轨迹：对于n x m的二维数组，n行，m列。
    x =i x n + j 可以表示一个数组元素的坐标。
    在解析时，(x/n，x%n)即为原二维数组元素的坐标。

    因为后面要求的值都可以由之前的历史数据推导而求得。

     */



   // public  int [][] minPatch(int [][] grid)
    public int minPathSum(int[][] grid)
    {


        int m=grid.length;//m 行

        int n=grid[0].length;//n 列
        this.size=n;
        //定义一个数组，其角标，通过 x/n x%n   可以唯一表示一个二维数组的的教教
        int [] p= new int[m*n];



        //  p[m+n]=(m-1)*n+(n-1);
        //由于到达某一个点的路径，是根据之前上方元素标和左边元素的路径和比较后求得的。
        //所以历史路径轨迹只有到达终点时才知道。
        //在某一的位点元素为当前元素时，可以知道上一个元素的位置。
        //因此如果在所有当前位置记录上一个位置，从重点倒推可以得到轨迹。




        int [][] s= new int[m][n];
        s[0][0]=grid[0][0];
        //s[m-1][n-1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n ; j++) {

                if(j>0&&i>0)
                {
                    //s[i][j]=grid[i][j]+Math.min( s[i-1][j], s[i][j-1]);
                    int min=Integer.MIN_VALUE;
                    if(s[i-1][j]>s[i][j-1])
                    {
                      min=s[i][j-1];

                        //通过i j坐标计算得到索引，其值为上一步坐标计算得到的值。
                        //在后续可以由此值计算出上一步的坐标.
                        //p[]的角标为i j计算得到，其值为上一步的坐标计算得到。因此逆向可以推出路径。
                        p[countIndex(i,j)]=countIndex(i,j-1);
                    }
                    else
                    {
                        min=s[i-1][j];
                        p[countIndex(i,j)]=countIndex(i-1,j);
                    }

                    s[i][j]=grid[i][j]+min;

                }


                else if(j>0) {

                    s[i][j] = grid[i][j] + s[i][j - 1];
                    p[countIndex(i,j)]=countIndex(i,j-1);

                }

                else if(i>0)
                {
                    s[i][j]=grid[i][j]+s[i-1][j];
                    p[countIndex(i,j)]=countIndex(i-1,j);
                    //
                }
            }

        }


        //从起点[0][0]移动到[m-1][n-1]需要移动m+n-1个点
        int [][] pls=new int[m+n-1][2] ;

        pls[m+n-2]=new int[]{m-1,n-1};
        //终点点坐标的索引
        /*
           p[countIndex(i,j)]=countIndex(i,j-1);
          或    p[countIndex(i,j)]=countIndex(i-1,j);
         */
        //由之前的代码可知，当前的idx 作为数组的角标，取出的值为上一个点的坐标计算得到的索引值。
        int idx=countIndex(m-1,n-1);
        //终点角标
        System.out.println(pls[m+n-2][0]+"  "+pls[m+n-2][1]);
        //
        for (int i = m+n-3; i >=0; i--) {
            pls[i]= parsePath(p[idx]);//根据索引可以反向解析出坐标
            idx=p[idx];

           // System.out.println(parsePath(p[i])[0]+"  "+parsePath(p[i])[1]);

            System.out.println(pls[i][0]+"  "+pls[i][1]);
        }


        return s[m-1][n-1];

     // return pls;



    }



    public  int countIndex(int i, int j)
    {

        return i*this.size+j;

    }

    public  int [] parsePath(int k)
    {
       return new int[]{k/this.size,k%size};
    }


    public static void main(String[] args) {
     new 最小路径和的轨迹64进阶().minPathSum(
        new int [][]{{1,3,1},{1,5,1},{4,2,1}});
    }
}
