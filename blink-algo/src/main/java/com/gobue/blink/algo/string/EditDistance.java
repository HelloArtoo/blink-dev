package com.gobue.blink.algo.string;

/**
 * 动态规划：编辑距离<br/>
 * 状态转移方程: d[i, j]=min(d[i-1, j] + 1, d[i, j-1] + 1, d[i-1, j-1] + r(i, j))
 */
public class EditDistance {

    /**
     * <pre>
     * 如果 i 为 0，且 j 也为 0，那么 d[i, j] 为 0。
     * 如果 i 为 0，且 j 大于 0，那么 d[i, j] 为 j。
     * 如果 i 大于 0，且 j 为 0，那么 d[i, j] 为 i。
     * 如果 i 大于 0，且 j 大于 0，那么 d[i, j]=min(d[i-1, j] + 1, d[i, j-1] + 1, d[i-1, j-1] + r(i, j))。
     * </pre>
     *
     * @param a
     * @param b
     * @return
     */
    // 请注意由于Java语言实现的关系，代码里的状态转移是从d[i, j]到d[i+1, j+1]，而不是从d[i-1, j-1]到d[i, j]。本质上是一样的。
    public static int getDistance(String a, String b) {

        int[][] martix = new int[a.length() + 1][b.length() + 1];

        //初始化
        for (int i = 0; i <= a.length(); i++) {
            martix[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            martix[0][j] = j;
        }

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                int r = 0;
                if (a.charAt(i) != b.charAt(j)) {
                    r = 1;
                }

                martix[i + 1][j + 1] = Math.min(Math.min(martix[i][j + 1] + 1, martix[i + 1][j] + 1), martix[i][j] + r);
            }
        }


        return martix[a.length()][b.length()];
    }

    public static void main(String[] args) {
        System.out.println(getDistance("mouse", "mouuuse"));
    }
}
