package com.gobue.blink.algo.array;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

    /**
     * leet:179
     * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [10,2]
     * 输出: 210
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        if (nums.length < 1) {
            return "";
        }

        String[] s = new String[nums.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });

        //为0的情况
        if ("0".equals(s[0])) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        Arrays.sort(arr);
    }
}
