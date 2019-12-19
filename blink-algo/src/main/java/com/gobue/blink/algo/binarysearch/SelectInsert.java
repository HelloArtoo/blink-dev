package com.gobue.blink.algo.binarysearch;

/**
 * <pre>
 *
 *     给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 *     你可以假设数组中无重复元素。
 *
 *
 * </pre>
 */
public class SelectInsert {


    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            //右中位数
            int mid = (left + right + 1) >>> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return target > nums[left] ? left + 1 : left;
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            //夹逼定律，先排出掉不可能的
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        if(nums[left] == target) {
            return left;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7};
        System.out.println(searchInsert(arr, 1));
    }
}
