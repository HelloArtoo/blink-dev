package com.gobue.blink.algo.sort;

import java.util.Arrays;

public class CountingSort {

    /**
     * 计数排序
     * <br/>
     * 1、获取计数范围，初始化计数数组
     * 2、初始化计数数组，并统计每项的累计值
     * 3、用临时数组回归排序后的数据
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null) {
            return;
        }

        //1、获取计数范围，创建计数数组
        int[] c = initCountingArr(arr);
        //2、初始化计数数组并计算每项累计值
        sumCountingArr(c, arr);
        //3、用临时数组回归排序后的数据
        countingSort(c, arr);
    }

    private void countingSort(int[] c, int[] arr) {
        int[] tmp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int index = c[arr[i]] - 1;
            tmp[index] = arr[i];
            c[arr[i]]--;
        }

        //赋值到arr
        for (int j = 0; j < arr.length; j++) {
            arr[j] = tmp[j];
        }
    }

    private void sumCountingArr(int[] c, int[] arr) {
        //计数
        for (int i = 0; i < arr.length; i++) {
            c[arr[i]]++;
        }

        //累计值
        for (int j = 1; j < c.length; j++) {
            c[j] = c[j - 1] + c[j];
        }
    }

    private int[] initCountingArr(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return new int[max + 1];
    }

    public static void main(String[] args) {

        int[] samples = {100, 99, 98, 19, 20, 3, 0, 30, 33, 32, 18, 5, 2, 4, 4, 4, 77, 29, 19};
        new CountingSort().sort(samples);
        System.out.println(Arrays.toString(samples));
    }
}
