package com.gobue.blink.algo.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序，分而治之的思想
 */
public class QuickSort {

    private static final Random random = new Random();


    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 1、中枢点方法
     *
     * @param nums
     * @param l
     * @param r
     */
    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        //随机的方式选择pivot点
        int pivot = l + random.nextInt(r - l);
        pivot = partition(nums, l, r, pivot);
        quickSort(nums, l, pivot - 1);
        quickSort(nums, pivot + 1, r);
    }

    private static int partition(int[] nums, int l, int r, int pivot) {
        int pivot_val = nums[pivot], store_index = l;
        //1、move to end
        swap(nums, pivot, r);
        //2、move all smaller elements to the left.
        for (int i = l; i <= r; i++) {
            if (nums[i] < pivot_val) {
                swap(nums, store_index, i);
                store_index++;
            }
        }
        //3、move pivot to its final place
        swap(nums, store_index, r);
        return store_index;
    }

    private static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /**
     * 2、填坑法
     *
     * @param nums
     * @param l
     * @param r
     */
    private void quickSort2(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        //找到中枢位置
        int pivot = nums[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            nums[i] = nums[j];

            while (i < j && nums[i] <= pivot) {
                i++;
            }
            nums[j] = nums[i];
        }

        nums[i] = pivot;
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, 1, 4, 0, 1, 9};
        new QuickSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
