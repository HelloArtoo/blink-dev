package com.gobue.blink.algo.backtracing;

import java.util.*;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FullPermutation {

    /**
     * 回溯1
     *
     * @param nums
     * @return
     */
    //回溯算法 - 递归完成后状态回置 这才是回溯
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 1) {
            return res;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, deque, used, res);
        return res;
    }

    public void dfs(int[] nums, int len, int depth, Deque<Integer> deque, boolean[] used, List<List<Integer>> res) {
        //首先定义出口
        if (len == depth) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            deque.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, deque, used, res);
            //递归完下一层之后，需要回溯到上一层的状态
            used[i] = false;
            deque.removeLast();
        }
    }

    /**
     * 回溯方式2：collections. swap
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {

        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 1) {
            return res;
        }

        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            lst.add(nums[i]);
        }

        dfs2(nums, lst, len, 0, res);
        return res;
    }

    private void dfs2(int[] nums, List<Integer> lst, int len, int depth, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(lst));
            return;
        }

        for (int i = depth; i < len; i++) {
            Collections.swap(lst, i, depth);
            dfs2(nums, lst, len, depth + 1, res);
            Collections.swap(lst, depth, i);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new FullPermutation().permute2(nums));
    }
}
