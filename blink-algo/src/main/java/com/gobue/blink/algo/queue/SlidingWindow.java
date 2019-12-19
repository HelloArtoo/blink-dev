package com.gobue.blink.algo.queue;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 */
public class SlidingWindow {

    //TODO 动态规划的方式取

    /**
     * 双端队列的方式
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowForDeque(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int[] rst = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int rstIndex = i - k + 1;
            //弹出左边
            if (i >= k && rstIndex > deque.peekFirst()) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (rstIndex >= 0) {
                rst[rstIndex] = nums[deque.peekFirst()];
            }
        }
        return rst;
    }


    /**
     * <pre>
     *     给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *     返回滑动窗口中的最大值。
     * </pre>
     * <p>
     * 堆的解法
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (nums == null || len == 0) {
            return nums;
        }

        int[] rst = new int[len < k ? 1 : len - k + 1];
        int index = 0;
        //大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            public int compare(Integer p1, Integer p2) {
                return p2 > p1 ? 1 : (p2 == p1 ? 0 : -1);
            }
        });

        for (int i = 0; i < nums.length; i++) {
            if (heap.size() < (k - 1)) {
                heap.offer(nums[i]);
                rst[0] = heap.peek();
                continue;
            }
            heap.offer(nums[i]);
            rst[index++] = heap.peek();
            heap.remove(nums[i - k + 1]);
        }

        return rst;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
    }

}
