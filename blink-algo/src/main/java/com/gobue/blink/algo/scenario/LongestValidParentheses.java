package com.gobue.blink.algo.scenario;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestValidParentheses {

    /**
     * 暴力法： 每次对偶数长度的子串进行判断，取max
     *
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        int len = s.length();
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 2; j <= len; j += 2) {
                if (isValid(s.substring(i, j))) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }


    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push('(');
            } else if (!stk.empty() && stk.peek() == '(') {
                stk.pop();
            } else {
                return false;
            }
        }
        return stk.empty();
    }

    /**
     * 方式2： 动态规划
     *
     * @param s
     * @return
     */
    //有效的字符串肯定是以')'结尾的
    //如果 s[i]==')',s[i-1`]=='('    dp方式： dp[i] = dp[i-2] + 2
    //如果 s[i]==')',s[i-1`]==')' 如果s[i−dp[i−1]−1]==‘(’     dp方式： dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
    public int longestValidParentheses2(String s) {
        int len = s.length();
        int[] dp = new int[len];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i > 2 ? i - 2 : 0] + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] > 2 ? i - dp[i - 1] - 2 : 0] + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * 方式3：栈的方式
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s) {
        int len = s.length();
        int max = 0;
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(-1);
        for(int i = 0; i < len; i++) {
            if(s.charAt(i) == '(') {
                stk.push(i);
            }else {
                stk.pop();
                if(stk.empty()) {
                    stk.push(i);
                } else {
                    max = Math.max(max,  i - stk.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "())((())";
        s = ")()())";
        System.out.println(new LongestValidParentheses().longestValidParentheses3(s));
    }
}
