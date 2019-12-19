package com.gobue.blink.algo.dp;

public class MaxSubArray {

    /**
     * 动态规划的思想：
     * 1.假如全是负数，那就是找最大值即可，因为负数肯定越加越大。
     * 2.如果有正数，则肯定从正数开始计算和，不然前面有负值，和肯定变小了，所以从正数开始。
     * 3.当和小于零时，这个区间就告一段落了，然后从下一个正数重新开始计算(也就是又回到 2 了)。而 dp 也就体现在这个地方。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int subSum = nums[0], max = subSum;
        for (int i = 1; i < nums.length; i++) {
            if (subSum > 0) {
                //算子序列和
                subSum += nums[i];
            } else {
                //如果非正整数，则从下一个正整数开始算最大子序列
                subSum = nums[i];
            }
            max = Math.max(subSum, max);
        }
        return max;
    }
}
