package com.gobue.blink.algo.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstring {

    /**
     * 滑动窗口，如果 s[j] 在 [i, j) 范围内有与 j
     * 重复的字符，我们不需要逐渐增加 i 。 我们可以直接跳过 [i，j']
     * 范围内的所有元素，并将 i 变为 j' + 1
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        int max = 1, p = 0, len = s.length();
        //可以用HashMap，但是优化后用这个
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < len; i++) {
            p = Math.max(p, map.getOrDefault(i, 0));
            max = Math.max(max, i - p + 1);
            map.put(s.charAt(i), i + 1);
        }

        return max;
    }

    /**
     * 暴力法
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            boolean[] asc = new boolean[128];
            asc[s.charAt(i)] = true;
            int sub = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (asc[s.charAt(j)]) {
                    break;
                } else {
                    asc[s.charAt(j)] = true;
                    sub++;
                }
            }
            max = Math.max(max, sub);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring2(s));
    }
}
