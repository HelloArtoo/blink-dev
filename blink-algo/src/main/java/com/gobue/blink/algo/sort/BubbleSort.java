package com.gobue.blink.algo.sort;

import java.util.Arrays;

public class BubbleSort {


    /**
     * 冒泡大法 (原地排序、稳定)
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            boolean flag = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            //优化，当一轮循环下来没有需要交换数据，证明已经有序直接终止
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, 1, 4, 0, 1, 9};
        new BubbleSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
