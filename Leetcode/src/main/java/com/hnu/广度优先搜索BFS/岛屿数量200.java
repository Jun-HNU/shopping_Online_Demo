package com.hnu.广度优先搜索BFS;





public class 岛屿数量200 {

    void dfs(char[][] grid,int i,int j)
    {
        //超出数组边界返回
        if(i<0||j<0||i>grid.length-1||j>grid[0].length-1) return;
        //当前（i，j）已经是0，了直接返回
        if(grid[i][j]=='0') return;
        grid[i][j]='0';//让当前陆地淹没，方便标记
        dfs(grid,i-1,j);//向周围扩张
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }

    public int numIslands(char[][] grid) {
        int x=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]=='1')
                {
                    //由于def中将同一块陆地全部标记为淹没，这里发现的陆地，必定为新陆地
                    x++;
                    dfs(grid,i,j);
                }
            }

        }



        return x;
    }
}
