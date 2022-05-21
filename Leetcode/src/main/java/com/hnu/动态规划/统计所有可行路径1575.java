package com.hnu.动态规划;

import java.util.Arrays;



/*

这里的关键是，将剩余油量作为数组的角标。
这里关键点在于将题目所求的值进行转化。题目求的是，
给定数组locations[],start，finish 和 fuel 分别表示城市的集合，出发城市、目的地城市和你初始拥有的汽油总量，求路径总数
也就是说答案是唯一的，即可以推导出当locations[]确定和fule确定时，从start出发到finsh的所有路径总是确定的，那么这就是一个记忆化搜索问题了。
从start出发到达以一个位置x,剩余油量fule-(stat-x)时，再以x为起点时，所有可行的路径任然是确定的。
那么我们可以将这种场景用数组去记录下来。
即对于唯一的start和fule,此时的所有可行路径是确定的。
f[start][fule]可以唯一表示此时的所哟所有可行路径。
对于边界条件
1.没有油
2.剩余油量不足以到达下一个位置。


由于是记忆化搜索，并且除了初始化外，f[i][j]表示的剩余路径是确定的，也就是只可能被赋值一次。
那么我们可以对所有f[i][j]初始化为-1，赋值过一次后绝对是大于0的，当后续访问时直接返回该值。


*/


public class 统计所有可行路径1575 {
    static final int MOD = 1000000007;
    int[][] f;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        f = new int[locations.length][fuel + 1];
        for (int[] row : f) {
            Arrays.fill(row, -1);
        }
        return dfs(locations, start, finish, fuel);
    }

    public int dfs(int[] locations, int pos, int finish, int rest) {
        if (f[pos][rest] != -1) {
            return f[pos][rest];//之前计算过，这里不再计算
        }

        f[pos][rest] = 0;//如果预估油量不足，直接返回
        if (Math.abs(locations[pos] - locations[finish]) > rest) {
            return 0;
        }

        int n = locations.length;
        //当前位置为pos,
        for (int i = 0; i < n; ++i) {//当前层的每一个节点都要被遍历到
            if (pos != i) {//当前位置忽略，继续遍历下一个位置
                int cost;//到达下一个位置，消耗油量
                if ((cost = Math.abs(locations[pos] - locations[i])) <= rest) {
                    /*如果当前位置pos不足以到达下一位置i,就跳过当前城市i,否则进入递归求，
                   当前位置为i，终点位置为 finish,剩余油量为 rest - cost时的所有可行的路径。
                    */

                    //以当前位置为起点，继续寻找下一个位置
                    f[pos][rest] += dfs(locations, i, finish, rest - cost);
                    //当前层的下一个位置，继续寻找
                    f[pos][rest] %= MOD;
                }
            }
        }
        if (pos == finish) {
            f[pos][rest] += 1;//如果起点和重点重合也算一条路径。
            f[pos][rest] %= MOD;
        }
        return f[pos][rest];
    }
}

