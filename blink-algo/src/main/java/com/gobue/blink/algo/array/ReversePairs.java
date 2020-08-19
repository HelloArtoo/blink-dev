package com.gobue.blink.algo.array;

/**
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * <p>
 * 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 * <p>
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReversePairs {

    public int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        return mergeSortCount(nums, 0, nums.length - 1);

    }

    private int mergeSortCount(int[] nums, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int p = (l + r) >>> 1;
        //左右分别的逆序对数量
        int count = mergeSortCount(nums, l, p) + mergeSortCount(nums, p + 1, r);
        //整体的逆序对数量
        int j = p + 1;
        for (int i = l; i <= p; i++) {
            while (j <= r && nums[i] > nums[j] * 2L) {
                j++;
            }
            count += j - (p + 1);
        }
        mergeSortedArray(nums, l, p, r);
        return count;
    }

    private void mergeSortedArray(int[] nums, int l, int p, int r) {
        int[] tmp = new int[r - l + 1];
        int i = l, j = p + 1, k = 0;
        while (i <= p && j <= r) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }

        while (i <= p) {
            tmp[k++] = nums[i++];
        }

        while (j <= r) {
            tmp[k++] = nums[j++];
        }

        for (k = 0; k < tmp.length; k++) {
            nums[l++] = tmp[k];
        }
    }

    public static void main(String[] args) {
        int[] nums = {2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647};
        System.out.println(new ReversePairs().reversePairs(nums));
    }
}
