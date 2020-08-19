package com.gobue.blink.algo.array;

import java.util.Arrays;

/**
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * <p>
 * 如果不能形成任何面积不为零的三角形，返回 0。
 */
public class LargestPerimeter {

    /**
     * 巧妙的解法，一层循环就够，三条边在一起的
     * <p>
     * 三角形的判断条件：
     * 假设A<=B<=c当 A+B > C则可以组成一个三角形
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; i--) {
            if ((A[i] + A[i + 1]) > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }
        return 0;
    }


    //三重循环？（最初的想法）
    public int largestPerimeter2(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            for (int j = i - 1; j >= 1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (A[k] > (A[i] - A[j])) {
                        return A[i] + A[j] + A[k];
                    }
                }
            }
        }
        return 0;
    }

}
