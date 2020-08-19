package com.gobue.blink.algo.dp;

public class MaxProduct {

    //dp ，有正数有负数，如果当前是负数则拿前面的最小值来乘，如果当前是正书，则拿前面的最大值来乘
    // dp[i][0] = nums[i]>=0 ? dp[i-1][0] * nums[i] : dp[i-1][1] * nums[i]
    //
    public static int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[][] dp = new int[2][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int x = 0, y = 0, max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //滚动数组的技巧
            x = i % 2;
            y = (i - 1) % 2;
            if (nums[i] >= 0) {
                //最大值
                dp[x][0] = Math.max(dp[y][0] * nums[i], nums[i]);
                //最小值
                dp[x][1] = Math.min(dp[y][1] * nums[i], nums[i]);
            } else {
                //最大值
                dp[x][0] = Math.max(dp[y][1] * nums[i], nums[i]);
                //最小值
                dp[x][1] = Math.min(dp[y][0] * nums[i], nums[i]);
            }
            max = Math.max(dp[x][0], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, -2, 4};
        System.out.println(maxProduct(arr));
    }
}
