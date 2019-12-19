package com.gobue.blink.algo.scenario;

import java.util.Stack;

public class Pattern132 {

    /**
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     * <p>
     * 注意：n 的值小于15000。
     * <p>
     * 示例1:
     * <p>
     * 输入: [1, 2, 3, 4]
     * <p>
     * 输出: False
     * <p>
     * 解释: 序列中不存在132模式的子序列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/132-pattern
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        Integer[] right = new Integer[nums.length];
        //找到右边的第一个大于cur的数
        for (int i = nums.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right[i] = nums[stack.pop()];
            }
            stack.push(i);
        }
        //左边最小的数值
        int left = nums[0];
        for (int j = 1; j < nums.length; j++) {
            if (nums[j - 1] < left) {
                left = nums[j - 1];
            }
            if (right[j] != null && left < right[j] && nums[j] > right[j]) {
                return true;
            }
        }
        return false;
    }

    public static boolean find132pattern2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int[] min = new int[nums.length];
        min[0] = nums[0];
        //固定左边的最小值
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min[i]) {
                //弹出比min还小的
                while (!stack.isEmpty() && stack.peek() <= min[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && nums[i] > stack.peek()) {
                    return true;
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(find132pattern2(new int[]{1, 0, 1, -4, -3}));
    }
}
