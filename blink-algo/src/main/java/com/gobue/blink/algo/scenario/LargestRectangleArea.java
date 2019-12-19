package com.gobue.blink.algo.scenario;

import java.util.Stack;

public class LargestRectangleArea {

    /**
     * leetcode 84.
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * <p>
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int area = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            //增加一个末尾的-1指针，方便计算
            int cur = (i == heights.length ? -1 : heights[i]);
            //单调栈，单调递减
            while (!stack.isEmpty() && cur < heights[stack.peek()]) {
                Integer top = stack.pop();
                if (!stack.isEmpty()) {
                    area = Math.max(area, heights[top] * (i - stack.peek() - 1));
                } else {
                    area = Math.max(area, heights[top] * i);
                }
            }
            stack.push(i);
        }
        return area;
    }

    public static void main(String[] args) {

    }
}
