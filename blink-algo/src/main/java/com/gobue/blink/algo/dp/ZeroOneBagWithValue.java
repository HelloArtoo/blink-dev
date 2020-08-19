package com.gobue.blink.algo.dp;

/**
 * 0-1背包变种版本
 * 东西含有价值
 * 对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？
 */
public class ZeroOneBagWithValue {

    /**
     * 我们用一个二维数组 states[n][w+1]，来记录每层可以达到的不同状态。不过这里数组存储的值不再是 boolean 类型的了，
     * 而是当前状态对应的最大总价值。我们把每一层中 (i, cw) 重复的状态（节点）合并，
     * 只记录 cv 值最大的那个状态，然后基于这些状态来推导下一层的状态。
     *
     * @param weight
     * @param value
     * @param n
     * @param capacity
     * @return
     */
    public static int knapsack(int[] weight, int[] value, int n, int capacity) {
        int[][] states = new int[n][capacity + 1];
        //1、先初始化价值，均为-1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                states[i][j] = -1;
            }
        }

        //2、初始化哨兵，第一行
        states[0][0] = 0;
        if (weight[0] < capacity) {
            states[0][weight[0]] = value[0];
        }

        //3、状态转移
        for (int i = 1; i < n; i++) {
            //不选择第i个物品的时候
            for (int j = 0; j <= capacity; j++) {
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            //选择第i个物品
            for (int j = 0; j <= capacity - weight[i]; j++) {
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        //4、找出最大值
        int max = -1;
        for (int j = capacity; j >= 0; j--) {
            if (states[n - 1][j] > max) {
                max = states[n - 1][j];
            }
        }

        return max;
    }

    //用一纬数组优化空间
    public static int knapsack2(int[] weight, int[] value, int n, int capacity) {
        int[] states = new int[capacity + 1];
        //初始化
        for (int i = 0; i <= capacity; i++) {
            states[i] = -1;
        }

        //哨兵
        states[0] = 0;
        if (weight[0] < capacity) {
            states[weight[0]] = value[0];
        }

        //状态转移
        for (int i = 1; i < n; i++) {
            for (int j = capacity - weight[i]; j >= 0; j--) {
                if (states[j] >= 0) {
                    int v = states[j] + value[i];
                    if (v > states[j + weight[i]]) {
                        states[j + weight[i]] = v;
                    }
                }
            }
        }

        //4、找出最大值
        int max = -1;
        for (int j = capacity; j >= 0; j--) {
            if (states[j] > max) {
                max = states[j];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] weights = {6, 3, 2, 2, 6};
        int[] vals = {1, 2, 3, 4, 5};
        int capacity = 11;
        System.out.println(ZeroOneBagWithValue.knapsack(weights, vals, weights.length, capacity));
        System.out.println(ZeroOneBagWithValue.knapsack2(weights, vals, weights.length, capacity));
    }

}
