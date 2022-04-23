package com.hnu.动态规划;

import java.util.List;

public class 三角形最小路径和120 {


    /*
    求从顶点出发到最底部的最小路径，可以转化为从底部出发，到达顶点的最小路径。
    将每一行所有最小状态的值计算出来，就可以推到出结果
     */

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size()-1; i>0; i--) {

            for (int j = triangle.get(i).size()-2; j >=0; j--) {


                if(triangle.get(i).get(j)<triangle.get(i).get(j+1))
                {

                    //自下向上遍历二维数组元素，将较小的元素相加到上一层。
                    //上一层的每个元素，只可能与当前层的一个元素相加。
                    triangle.get(i-1).set(j,triangle.get(i-1).get(j)+triangle.get(i).get(j));
                }
                else
                {
                    triangle.get(i-1).set(j,triangle.get(i-1).get(j)+triangle.get(i).get(j+1));
                }

            }

        }

        return  triangle.get(0).get(0);

    }



}
