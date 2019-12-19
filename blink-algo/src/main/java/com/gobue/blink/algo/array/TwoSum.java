package com.gobue.blink.algo.array;

import java.util.HashMap;
import java.util.Map;


public class TwoSum {

    /**
     * <pre>
     *     /**
     *  * 给定 nums = [2, 7, 11, 15], target = 9
     *  * <p>
     *  * 因为 nums[0] + nums[1] = 2 + 7 = 9
     *  * 所以返回 [0, 1]
     * </pre>
     * map方式
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        // 数组中的值为key，下标index为value
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int subtractVal = target - nums[i];
            // 若 map 中包含 key 为 subtractVal，则说明数组中包含两个数加起来等于 target 的值
            // 返回他们的下标
            if (map.containsKey(subtractVal)) {
                return new int[]{i, map.get(subtractVal)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * * <pre>
     *   给定 nums = [2, 7, 11, 15], target = 9
     *   因为 nums[0] + nums[1] = 2 + 7 = 9
     *   所以返回 [0, 1]
     *   </pre>
     * 求和
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int n = 2048;
        int[] map = new int[n];
        int max = n - 1;
        for (int i = 0; i < nums.length; i++) {
            int b = map[(target - nums[i]) & max];
            if (b != 0) {
                return new int[]{b - 1, i};
            }
            map[nums[i] & max] = i + 1;
        }
        return null;
    }


    /**
     * <pre>
     *给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * 说明:
     *
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * </pre>
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumForSortedArr(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if(l + r == target) {
                return new int[]{l+1, r+1};
            } else if(l + r < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1,-1};
    }



    public static void main(String[] args) {

        System.out.println(twoSum2(new int[]{1, 20, 30}, Integer.MAX_VALUE));
    }
}

