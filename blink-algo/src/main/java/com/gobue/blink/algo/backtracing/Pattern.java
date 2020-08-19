package com.gobue.blink.algo.backtracing;

/**
 * 正则表达式匹配
 * 回溯方式
 */

public class Pattern {

    private boolean matched = false;
    private char[] pattern;
    private int p_len;

    public Pattern(char[] pattern, int p_len) {
        this.p_len = p_len;
        this.pattern = pattern;
    }

    public boolean match(char[] text, int t_len) {
        matched = false;
        rmatch(0, 0, text, t_len);
        return matched;
    }

    /**
     * 我假设正则表达式中只包含“*”和“?”这两种通配符，并且对这两个通配符的语义稍微做些改变，
     * 其中，“*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个或者一个任意字符。
     * 基于以上背景假设，我们看下，如何用回溯算法，判断一个给定的文本，能否跟给定的正则表达式匹配？
     *
     * @param ti
     * @param pi
     * @param text
     * @param t_len
     */
    private void rmatch(int ti, int pi, char[] text, int t_len) {
        if (matched) {
            return;
        }

        //终止条件
        if (pi == p_len) {
            if (ti == t_len) {
                matched = true;
            }
            return;
        }

        //“*”匹配任意多个（大于等于 0 个）任意字符
        if (pattern[pi] == '*') {
            for (int k = 0; k <= t_len - ti; k++) {
                rmatch(ti + k, pi + 1, text, t_len);
            }
        } else if (pattern[pi] == '?') {//“?”匹配零个或者一个任意字符。
            rmatch(ti, pi + 1, text, t_len);
            rmatch(ti + 1, pi + 1, text, t_len);
        } else if (ti < t_len && pattern[pi] == text[ti]) {
            rmatch(ti + 1, pi + 1, text, t_len);
        }
    }

    public static void main(String[] args) {
        String pattern = "a?b";
        String text = "abb";
        boolean flag = new Pattern(pattern.toCharArray(), pattern.length()).match(text.toCharArray(), text.length());
        System.out.println(flag);
    }

}
