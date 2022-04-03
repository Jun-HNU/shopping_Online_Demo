package com.hnu.特殊数;



/*
编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」 定义为：

对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
如果这个过程 结果为 1，那么这个数就是快乐数。
如果 n 是 快乐数 就返回 true ；不是，则返回 false 。

 

示例 1：

输入：n = 19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
示例 2：

输入：n = 2
输出：false
 

提示：

1 <= n <= 231 - 1


 */
class 快乐数 {
    public boolean isHappy(int n){//每一个等式就是一个当前的函数。
    if (n==4)    // 为4时会出现无限循环
        //4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4
        return false;
    
    int rem = 0, sums = 0;
    while (n != 0){
        rem = n % 10;
        sums += rem * rem;
        n /= 10;
    }

    if (sums == 1)
        return true;
    else
        return isHappy(sums);
}
}
