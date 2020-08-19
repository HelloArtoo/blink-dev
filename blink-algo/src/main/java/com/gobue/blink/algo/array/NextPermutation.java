package com.gobue.blink.algo.array;

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        //下一个最大
        int i = nums.length - 2;
        for (; i >= 0; i--) {
            //右至左，找到第一个比右边小的数
            if (nums[i] < nums[i + 1]) {
                int j = nums.length - 1;
                while (j > i + 1 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
                break;
            }
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        //int[] arr = {1,  1};
        int[] arr = {1, 5, 8, 4, 7, 6, 5, 3, 1};
        new NextPermutation().nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }
}
