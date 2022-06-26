package com.hnu.动态规划DP;

class 出界的路径数576_2 {
    private Integer[][][] memo;
    private int mod = (int) 1e9+7;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        memo = new Integer[m][n][maxMove+1];
        int ans = dfs(startRow, startColumn, 0, m, n, maxMove);
        return ans;
    }

    public int dfs(int i, int j, int k, int m, int n, int maxMove) {
        if(i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if(k == maxMove) {
            return 0;
        }
        if(memo[i][j][k] != null) {
            return memo[i][j][k];
        }
        int cnt = 0;
        cnt = (cnt + dfs(i-1, j, k+1, m, n, maxMove)) % mod;
        cnt = (cnt + dfs(i+1, j, k+1, m, n, maxMove)) % mod;
        cnt = (cnt + dfs(i, j-1, k+1, m, n, maxMove)) % mod;
        cnt = (cnt + dfs(i, j+1, k+1, m, n, maxMove)) % mod;
        memo[i][j][k] = cnt;
        return memo[i][j][k];
    }
}