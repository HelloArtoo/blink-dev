package com.gobue.blink.algo.sort;

import java.util.Arrays;

/**
 * 桶排序
 */
public class BucketSort {

    /**
     * 每个桶的大小
     *
     * @param arr
     * @param bucketSize
     */
    public static void sort(int[] arr, int bucketSize) {
        if (arr.length < 2) {
            return;
        }
        int min = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            } else if (max < arr[i]) {
                max = arr[i];
            }
        }
        int bucketCount = initBucketCount(max, min, bucketSize);
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] volumns = new int[bucketCount];
        //入桶
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - min) / bucketSize;
            if (volumns[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][volumns[bucketIndex]] = arr[i];
            //容量加一
            volumns[bucketIndex]++;
        }

        //单桶排序
        int m = 0;
        for (int j = 0; j < buckets.length; j++) {
            if (volumns[j] == 0) {
                continue;
            }
            //排序
            QuickSort.quickSort(buckets[j], 0, volumns[j] - 1);
            for (int k = 0; k < volumns[j]; k++) {
                arr[m++] = buckets[j][k];
            }
        }
    }

    /**
     * 数组扩容
     *
     * @param buckets
     * @param bucketIndex
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        System.out.println("数组扩容:" + bucketIndex);
        int[] tmp = buckets[bucketIndex];
        int[] newArr = new int[tmp.length * 2];
        for (int i = 0; i < tmp.length; i++) {
            newArr[i] = tmp[i];
        }
        buckets[bucketIndex] = newArr;
    }

    /**
     * 初始化桶的数量
     *
     * @param min
     * @param max
     * @param bucketSize
     * @return
     */
    private static int initBucketCount(int max, int min, int bucketSize) {
        //富裕一个桶的数量
        return (max - min) / bucketSize + 1;
    }

    public static void main(String[] args) {
        int[] samples = {100, 99, 98, 19, 20, 3, 0, 30, 33, 32, 18, -5, -2, -4, 4, 4, 77, 29, 19};
        BucketSort.sort(samples, 2);
        System.out.println(Arrays.toString(samples));
    }

}
