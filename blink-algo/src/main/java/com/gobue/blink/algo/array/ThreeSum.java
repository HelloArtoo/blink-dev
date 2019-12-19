package com.gobue.blink.algo.array;

import java.util.*;

/**
 * <pre>
 *     给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 *      注意：答案中不可以包含重复的三元组。
 *
 *      例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 *      满足要求的三元组集合为：
 *      [
 *       [-1, 0, 1],
 *       [-1, -1, 2]
 *      ]
 * </pre>
 */
public class ThreeSum {

    /**
     * 家
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>  lst = new ArrayList<List<Integer>>();
        for(int i = 0; i < nums.length; i++) {
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int l = i+1, r = nums.length - 1;
            while(l < r) {
                int total = nums[i] + nums[l] + nums[r];
                if(total < 0) {
                    l++;
                }else if (total > 0) {
                    r--;
                }else {
                    lst.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while(l < r && nums[l] == nums[l+1] ) {
                        l++;
                    }
                    while(l < r && nums[r] == nums[r-1] ) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return lst;
    }

    //没通过
    @Deprecated
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>  lst = new ArrayList<List<Integer>>();
        for(int i = 0; i < nums.length; i++) {
            if(i>=1 && nums[i] == nums[i-1]) {
                continue;
            }
            Set<Integer> set = new HashSet<Integer>();
            for(int j = i + 1; j < nums.length; j++) {

                int substract = (- nums[i] - nums[j]);
                if(set.contains(substract)) {
                    lst.add(Arrays.asList(nums[i],nums[j], substract));
                } else {
                    set.add(nums[j]);
                }
            }
        }
        return lst;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{0,0,0,0}));
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
