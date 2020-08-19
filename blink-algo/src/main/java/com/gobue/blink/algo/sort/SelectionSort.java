package com.gobue.blink.algo.sort;

import java.util.Arrays;

public class SelectionSort {

    /**
     * 选择排序
     */
    public void sort(int[] arr) {
        if (arr == null) {
            return;
        }

        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, 1, 4, 0, 1, 9};
        new SelectionSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
