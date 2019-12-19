package com.gobue.blink.algo.sort;

import java.util.Arrays;

/**
 * 归并排序，分而治之的思想
 */
public class MergeSort {


    public void sort(int[] nums) {
        this.mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = (l + r) >>> 1;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        mergeSortedArr(nums, l, mid, r);
    }

    private void mergeSortedArr(int[] nums, int l, int mid, int r) {

        int[] tmp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            tmp[k++] = nums[i++];
        }

        while (j <= r) {
            tmp[k++] = nums[j++];
        }

        for (int m = 0; m < tmp.length; m++) {
            nums[l++] = tmp[m];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, 1, 4, 0, 1, 9};
        new MergeSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
