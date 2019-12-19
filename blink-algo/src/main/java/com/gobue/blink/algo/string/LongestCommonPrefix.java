package com.gobue.blink.algo.string;

public class LongestCommonPrefix {

    /**
     * 方式 1， indexOf
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }

        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(s) != 0) {
                s = s.substring(0, s.length() - 1);
                if (s.isEmpty()) {
                    return "";
                }
            }
        }
        return s;
    }

    /**
     * 方式2，
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    /**
     * 分治的算法，拆分&递归解决
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        return findPrefix(strs, 0, strs.length - 1);
    }

    private String findPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        int mid = (l + r) >>> 1;
        String left = findPrefix(strs, l, mid);
        String right = findPrefix(strs, mid + 1, r);
        return commonPrefix(left, right);
    }

    private String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

    //方法4：二分法
    //方法5：https://leetcode.com/articles/implement-trie-prefix-tree/  前缀树

    public static void main(String[] args) {
        String[] s = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(s));
        //System.out.println(s.substring(0, s.length()-1));
    }

}
