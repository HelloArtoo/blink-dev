package com.gobue.blink.algo.backtracing;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FullPermutation2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 1) {
            return res;
        }

        //先排序
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        Deque<Integer> deque = new ArrayDeque<>();
        dfs(nums, len, 0, deque, used, res);
        return res;
    }

    public void dfs(int[] nums, int len, int depth, Deque<Integer> deque, boolean[] used, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            //剪枝，核心在于这一行 !used[i-1]
            //used[i-1]是因为nums[i-1]在回退过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            deque.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, deque, used, res);
            used[i] = false;
            deque.removeLast();
        }
    }

    public static void main(String[] args) {

    }
}
