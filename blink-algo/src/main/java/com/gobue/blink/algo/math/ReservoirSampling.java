package com.gobue.blink.algo.math;

import java.util.Arrays;
import java.util.Random;

/**
 * 蓄水池抽样: n个里面取k个数，概率一样，且n可能非常大。
 */
public class ReservoirSampling {

    private static int[] pool;//所有数据
    private static int n = 1000000;//数据规模
    private static Random random = new Random();

    static {
        pool = new int[n];
        for (int i = 0; i < n; i++) {
            pool[i] = i;
        }
    }

    public static int[] sample(int k) {

        int[] arr = new int[k];
        //初始化
        for (int i = 0; i < k; i++) {
            arr[i] = i;
        }

        for (int j = k; j < n; j++) {
            int tmp = random.nextInt(j + 1);
            if (tmp < k) {
                arr[tmp] = pool[j];
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sample(100)));
    }

}
