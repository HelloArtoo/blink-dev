package com.gobue.blink.algo.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeIntervals {

    /**
     * 6ms
     *
     * @param intervals
     * @return
     */
    public int[][] mergeByLast(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        //按照第一位排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });
        int rstIndex = 1;
        int[] last = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (last[1] >= intervals[i][0]) {
                if (last[1] < intervals[i][1]) {
                    last[1] = intervals[i][1];
                }
            } else {
                intervals[rstIndex++] = intervals[i];
                last = intervals[i];
            }
        }
        return Arrays.copyOf(intervals, rstIndex);
    }

    public int[][] mergeByArray(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        //按照第一位排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));


        int rstIndex = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[rstIndex - 1][1]) {
                if (intervals[i][1] > intervals[rstIndex - 1][1]) {
                    intervals[rstIndex - 1][1] = intervals[i][1];
                }
            } else {
                intervals[rstIndex++] = intervals[i];
            }
        }
        return Arrays.copyOf(intervals, rstIndex);
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        //按照第一位排序
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] pre = stack.peek();
            int[] cur = intervals[i];
            //合并区间
            if (cur[0] >= pre[0] && cur[0] <= pre[1]) {
                stack.pop();
                stack.push(new int[]{pre[0], Math.max(pre[1], cur[1])});
            } else {
                stack.push(cur);
            }
        }

        int[][] rst = new int[stack.size()][2];
        for (int i = rst.length - 1; i >= 0; i--) {
            rst[i] = stack.pop();
        }
        return rst;
    }

    public static void main(String[] args) {
        LinkedList<int[]> lst = new LinkedList<>();

        int[] last = lst.getLast();
        lst.getLast();
    }
}
