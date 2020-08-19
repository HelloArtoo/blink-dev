package com.gobue.blink.algo.binarysearch;

public class FindSingularity {

    /**
     * 已知数组[1,2,3,2,9,8,7]其中位置3的2为奇点（连续递增或递减的数组段，出现一个反常值则为奇点）
     * 找到奇点
     *
     * @param arr
     * @return
     */
    public static int findSingularity(int[] arr) {
        if (arr == null) {
            return -1;
        }

        int left = 0, right = arr.length - 1;
        while (left < right) {

            int mid = (left + right) >>> 1;
            if (mid == 0) {
                break;
            }

            // if ((arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1])) {
            //  return mid;
            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else if (arr[mid] > arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            } else {
                return mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 2, 9, 8, 7};
        System.out.println(findSingularity(arr));
    }
}
