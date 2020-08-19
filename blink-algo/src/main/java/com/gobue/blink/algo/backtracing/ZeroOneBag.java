package com.gobue.blink.algo.backtracing;

/**
 * 0-1背包问题
 * 回溯方法解决
 */
public class ZeroOneBag {

    public static int W_MIN = Integer.MIN_VALUE;
    //TODO通过备忘录方式
    //private boolean[][] mem = new boolean[物品个数][背包容量 + 1]; // 备忘录，默认值false

    /**
     * @param i        放到第几个了
     * @param weight   放了多少重量了
     * @param things   放的东西数组
     * @param n        东西数量
     * @param capacity 背包总容量
     */
    public static void put(int i, int weight, int[] things, int n, int capacity) {
        if (i == n || weight == capacity) {
            if (weight > W_MIN) {
                W_MIN = weight;
            }
            return;
        }

        //if (mem[i][cw]) return; // 重复状态

        //0-1背包的递归代码里第11行非常巧妙，它借助回溯过程，实现了以每一个可能的物品，作为第一个装入背包的，以尝试所有物品组合。
        // 但如果仅按从前向后执行的顺序看，是不太容易发现这一点的。
        put(i + 1, weight, things, n, capacity);

        if (weight + things[i] <= capacity) {
            put(i + 1, weight + things[i], things, n, capacity);
        }
    }

    public static void main(String[] args) {
        int[] things = {6, 5, 5};
        put(0, 0, things, things.length, 18);
        System.out.println(W_MIN);
    }
}
