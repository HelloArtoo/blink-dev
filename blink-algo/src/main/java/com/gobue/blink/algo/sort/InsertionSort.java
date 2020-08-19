package com.gobue.blink.algo.sort;

import java.util.Arrays;

public class InsertionSort {

    /**
     * 插入排序<br/>
     * 第一步是找到插入位置，第二部是移动已经排序的数组
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null) {
            return;
        }

        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int val = arr[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                if (arr[j] > val) {
                    //数据移动
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            //补位
            arr[j + 1] = val;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, 1, 4, 0, 1, 9};
        new InsertionSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
