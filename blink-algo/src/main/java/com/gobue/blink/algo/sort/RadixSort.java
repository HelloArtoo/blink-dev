package com.gobue.blink.algo.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //从个位开始，对数组按"指数"进行排列，循环多少位
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    /**
     * 计数排序算法
     *
     * @param arr
     * @param exp
     */
    private static void countingSort(int[] arr, int exp) {
        if (arr.length <= 1) {
            return;
        }

        // 计算每个元素的个数
        int[] c = new int[10];
        for (int i = 0; i < arr.length; i++) {
            c[(arr[i] / exp) % 10]++;
        }

        // 计算排序后的位置
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) { //TODO 注意这里，从大到小
            r[c[(arr[i] / exp) % 10] - 1] = arr[i];
            c[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r[i];
        }
    }

    public static void main(String[] args) {
        int[] samples2 = {3,30,34,5,9};

        int[] samples = {22345, 12355, 144, 13252, 22223, 123, 63421, 13223, 43223, 22310, 1233, 67832, 99918, 21235, 123, 124};
        RadixSort.radixSort(samples2);
        System.out.println(Arrays.toString(samples2));
    }
}
