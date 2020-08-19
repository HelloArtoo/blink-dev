package com.gobue.blink.algo.array;

public class FindMedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            return findMedian(nums2);
        }

        if (nums2 == null) {
            return findMedian(nums1);
        }

        int[] arr = new int[nums1.length + nums2.length];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (nums1.length == index1) {
                arr[i] = nums2[index2++];
                continue;
            }

            if (nums2.length == index2) {
                arr[i] = nums1[index1++];
                continue;
            }

            if (nums1[index1] > nums2[index2]) {
                arr[i] = nums2[index2++];
            } else {
                arr[i] = nums1[index1++];
            }
        }
        return findMedian(arr);
    }

    private static double findMedian(int[] arr) {
        int mid = arr.length >>> 1;
        return arr.length % 2 == 0 ? (arr[mid] + arr[mid + 1]) / 2.0D : arr[mid] / 1.0D;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }

}

