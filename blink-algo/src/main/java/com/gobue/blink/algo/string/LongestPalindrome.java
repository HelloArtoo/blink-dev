package com.gobue.blink.algo.string;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindrome {

    //中心扩展法
    //核心是每个字母当中心，两边扩散
    //先找相同的字母，相同的字母算上，肯定是回文
    //直到左右两边都和中心不同了，比较左右两边的字符串内容
    //碰上不同的，则停止，以该字母为中心的最长子串找到了
    public static String longestPalindrome1(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        int len = s.length(), l = 0, r = 0, pstart = 0, plen = 0;
        for (int i = 0; i < len; i++) {
            l = i - 1;
            r = i + 1;
            char centra = s.charAt(i);
            int tmpLen = 1;
            //STEP 1 寻找相同的
            while (l >= 0 && s.charAt(l) == centra) {
                l--;
                tmpLen++;
            }

            while (r < len && s.charAt(r) == centra) {
                r++;
                tmpLen++;
            }

            //STEP 2 开始比较和中心不同的字符串
            while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                tmpLen += 2;
            }

            //STEP 3 到这儿已经找到该中心的最长回文了
            if (tmpLen > plen) {
                pstart = l + 1;
                plen = tmpLen;
            }
        }
        return s.substring(pstart, pstart + plen);
    }

    //DP版本
    //用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。试想如果 dp[l][r]=true，
    // 我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符，是不是减少了很多重复计算。
    public static String longestPalindromeDp(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        int start = 0, plen = 1, len = s.length();
        //记录状态
        boolean[][] dp = new boolean[len][len];
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                //r - l <= 2 其实是隐藏的初始化(a : r-l=0)  (aa : r-l=1) (aba : r-l=2) 为true，兼顾这三种情况
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > plen) {
                        plen = r - l + 1;
                        start = l;
                    }

                }

            }
        }
        return s.substring(start, start + plen);
    }


    public static void main(String[] args) {
        String s = "cbbd";
        String s2 = "abbbbb";
        String s3 = "ac";
        System.out.println(s3.substring(0, 2));
        // System.out.println(longestPalindrome1(s));
        System.out.println(longestPalindrome1(s2));
        System.out.println(longestPalindromeDp(s3));
        Character[] objects = new ArrayList<>().toArray(new Character[0]);
    }
}
