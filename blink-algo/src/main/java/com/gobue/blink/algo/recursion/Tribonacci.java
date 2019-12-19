package com.gobue.blink.algo.recursion;


/**
 * <pre>
 * 泰波那契序列 Tn 定义如下： 
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * </pre>
 */
public class Tribonacci {



    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return tribonacci(n - 3) + tribonacci(n - 2) + tribonacci(n - 1);
    }

    public static int tribonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int t0 = 0, t1 = 1, t2 = 1, tn = 0;
        for (int i = 3; i <= n; i++) {
            tn += (t0 + t1 + t2);
            t0 = t1;
            t1 = t2;
            t2 = tn;
        }
        return tn;
    }

    public static void main(String[] args) {
        System.out.println(tribonacci2(4));
    }
}
