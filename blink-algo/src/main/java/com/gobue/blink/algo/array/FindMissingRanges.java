package com.gobue.blink.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     Given sorted integer array where the range of the elements are in the inclusive range.
 *     [lower, upper] return its missing ranges.
 *
 *     for example: given [0,1,3,50,75], lower = 0 and upper = 99
 *     return ["2", "4-49", "51->74", "76->99"]
 *
 * </pre>
 */
public class FindMissingRanges {

    public static List<String> findMissingRangesList(int[] nums, int lower, int upper) {
        List<String> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }

        //左边界值判断
        addList(rst, lower, nums[0] - 1);

        int pre = nums[0], i = 1;
        //中间逻辑
        while (i < nums.length) {
            if (pre != nums[i] - 1) {
                addList(rst, pre + 1, nums[i] - 1);
            }
            pre = nums[i];
            i++;
        }

        //右边界值判断
        addList(rst, nums[i - 1] + 1, upper);
        return rst;
    }

    private static void addList(List<String> rst, int left, int right) {
        if (left == right) {
            rst.add(String.valueOf(left));
        } else if (left < right) {
            rst.add(left + "->" + right);
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 7, 50, 75};
        int lower = 0;
        int upper = 99;
        System.out.println(findMissingRangesList(nums, lower, upper));
    }
}
