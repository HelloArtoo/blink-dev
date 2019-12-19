package com.gobue.blink.algo.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterE {

    /**
     * 下一个更大元素 I  ，用单调栈实现
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums2.length);
        //单调栈
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stk.isEmpty() && nums2[i] > stk.peek()) {
                map.put(stk.pop(), nums2[i]);
            }
            stk.push(nums2[i]);
        }

        int[] rst = new int[nums1.length];
        for (int j = 0; j < nums1.length; j++) {
            rst[j] = map.getOrDefault(nums1[j], -1);
        }
        return rst;
    }


}
