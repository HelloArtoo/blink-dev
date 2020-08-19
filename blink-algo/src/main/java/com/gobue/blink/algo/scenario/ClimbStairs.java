package com.gobue.blink.algo.scenario;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbStairs {

    /**
     * 方法1： 递归，用哥memo变量剪枝
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climbStairs(0, n, memo);
    }

    public static int climbStairs(int steps, int n, int[] memo) {
        if (steps > n) {
            return 0;
        }

        if (steps == n) {
            return 1;
        }

        if (memo[steps] > 0) {
            return memo[steps];
        }

        memo[steps] = climbStairs(steps + 1, n, memo) + climbStairs(steps + 2, n, memo);
        return memo[steps];
    }


    /**
     * 方式2：动态规划: dp[i]=dp[i−1]+dp[i−2]
     *
     * @param n
     * @return
     */
    public static int climbStairsDp(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 方式3：斐波那契
     */
    public static int climbStairs3(int n) {
        if (n <= 2) {
            return n;
        }

        int step1 = 1, step2 = 2, rst = 0;
        for (int i = 3; i <= n; i++) {
            rst = step1 + step2;
            step1 = step2;
            step2 = rst;
        }
        return rst;
    }

    public static void main(String[] args) {

    }


}
