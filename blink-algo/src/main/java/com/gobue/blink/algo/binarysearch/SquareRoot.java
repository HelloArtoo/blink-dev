package com.gobue.blink.algo.binarysearch;

/**
 * 开根号
 */
public class SquareRoot {


    @Deprecated
    public static double sqrt(int v, double t) {

        double left = 1.0D;
        double right = v;
        while (true) {
            double middle = (left + right) / 2;
            double square = middle * middle;
            double delta = Math.abs(square - v);
            if (delta <= t) {
                return middle;
            } else if (square >= v) {
                right = middle;
            } else {
                left = middle;
            }
        }
    }

    @Deprecated
    public static double sqrt2(int v, double t) {
        if (v <= 1) {
            return -1.0;
        }

        double left = 1.0D;
        double right = v;
        while (true) {
            double middle = (left + right) / 2;
            double square = middle * middle;
            double delta = Math.abs((square / v) - 1);
            if (delta <= t) {
                return middle;
            } else if (square > v) {
                right = middle;
            } else {
                left = middle;
            }
        }
    }

    /**
     * <pre>
     *     实现 int sqrt(int x) 函数。
     *
     *      计算并返回 x 的平方根，其中 x 是非负整数。
     *
     *      由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     *
     * </pre>
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x < 1) {
            return 0;
        }

        int left = 1, right = x;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            long square = (long) mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int v = 10;
        double t = 0.000000000001;

        System.out.println("函数：" + Math.sqrt(v));
        System.out.println("本算法：" + sqrt(v, t));
        System.out.println("本算法2：" + sqrt2(v, t));
        System.out.println("本算法3：" + mySqrt(2147395599));


    }

}
