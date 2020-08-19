package com.gobue.blink.algo.dp;

/**
 * 动态规划
 * 0-1背包问题
 */
public class ZeroOneBag {

    /**
     * @param weights  东西数组
     * @param n        东西数量
     * @param capacity 背包容量
     * @return
     */
    public static int knapsack(int[] weights, int n, int capacity) {

        //记录每一层的状态
        boolean[][] states = new boolean[n][capacity + 1];
        //第一个特殊处理
        states[0][0] = true;
        if (weights[0] <= capacity) {
            states[0][weights[0]] = true;
        }

        //放东西
        for (int i = 1; i < n; i++) {
            //如果第i个不放进去
            for (int j = 0; j <= capacity; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }

            //第i个放进去
            for (int j = 0; j <= capacity - weights[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + weights[i]] = true;
                }
            }

            //fixme 打印二维数组，方便理解过程
            print(states);
        }

        //返回结果
        for (int i = capacity; i >= 0; i--) {
            if (states[n - 1][i]) {
                return i;
            }
        }

        return 0;
    }

    public static int knapsack2(int[] weights, int n, int capacity) {
        boolean[] states = new boolean[capacity + 1];
        //第一行数据哨兵
        states[0] = true;
        if (weights[0] <= capacity) {
            states[weights[0]] = true;
        }

        for (int i = 0; i < n; ++i) {
            //j 需要从大到小来处理。如果我们按照 j 从小到大处理的话，会出现 for 循环重复计算的问题
            for (int j = capacity - weights[i]; j >= 0; j--) {
                if (states[j]) {
                    //动态规划
                    states[j + weights[i]] = true;
                }
            }
        }

        for (int i = capacity; i >= 0; --i) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }


    private static void print(boolean[][] states) {

        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].length; j++) {
                System.out.print(states[i][j] ? "Q " : "* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] weights = {6, 3, 2, 2, 6};
        int capacity = 11;
        System.out.println(ZeroOneBag.knapsack(weights, weights.length, capacity));
        System.out.println(ZeroOneBag.knapsack2(weights, weights.length, capacity));
    }
}
