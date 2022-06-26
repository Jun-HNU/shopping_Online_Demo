package com.hnu.动态规划DP;

class 统计所有可行路径1575_Solution {
/*
locations[i] , finsh,fuel=y.
locations[i+1], finsh, fuel=y-(locations[i+1]-locations[i])
if(i==fish) count++;

方案一：
从start 出发到finish，对于任意设定的总油量fule,都有唯一的总路径数sum。
f[i][j] 表示从任意i出发到达j时,总油量为fule时的总路径数。

方案二：
location[i]
用f[i][j]表示从任意的i出发，总油量为j时到达目的地finish的总路径数。
则可设计二维数组f[][]来存储我们需要求的结果，并且这个值具有无后效性。并且我们还可推出
f[i][j+1]包含了f[i][j]的情形。


int dfs(int[] ls, int u, int end, int fuel) {}
其中，ls 参数和 end 参数分别代表源输入的 locations 和 finish，在整个 DFS 过程都不会变化，属于不变参数。

而 u 参数和 fuel 参数则是代表了 DFS 过程中的当前位置和当前油量，属于变化参数。

因此我们可以定一个 f[][] 二维数组，来分别表示两个可变参数。
因此选方案二。
用f[i][j]表示从任意的i出发，总油量为j时到达目的地finish的总路径数。并且油量大的情形可能总包含油量小的情形。





 */
int mod = 1000000007;

    int [][] f=null;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {

        if(fuel<Math.abs(locations[start]-locations[finish])) return 0;

        f=new int[locations.length][fuel+1];
//
        // f[i][j] 代表从位置 i出发，当前剩余油量为 j 的前提下，到达目的地的路径数量。
        //所以无论油量为多少，如果目的地就是当前位置，那么路径数最少为1
        for (int j = 0; j <fuel+1 ; j++) {
            f[finish][j]=1;//对于每个城市，自己去自己城市的总路径数为1.
        }


        for (int i = 0; i <fuel+1 ; i++) {//当前的油量，当油量为i+1时包含的路径数绝对比油量为i时要多，由油量少逐渐推到导至油量多。
            for (int j = 0; j < f.length; j++) {//当前城市j
                for (int k = 0; k <f.length ; k++) {//要去的城市k
                    if(j!=k) {//要去的下一个城市不是本身
                        int need = Math.abs(locations[j] - locations[k]);
                        if (i >= need)//从j到k要用去的油量比剩余油量大

                        {/*f[k][i - need];表示如果油量为i - need时，从k出发到达目的finish的总路径数，
                        显然如果油量为i,那就可以先使用need单位的油到达k,再从k到达目的地
                          */
                            f[j][i] = f[j][i] + f[k][i - need];
                            f[j][i] = f[j][i] % mod;
                        }
                    }
                }


            }

        }
       // f[i][j] 代表从位置 i出发，当前剩余油量为 j 的前提下，到达目的地的路径数量。
        return f[start][fuel];

    }
    public static void main(String[] args) {



        int [] l= new int[]{2,3,6,8,4};
       // countRoutes(l, 1, 3, 5);

    }


}