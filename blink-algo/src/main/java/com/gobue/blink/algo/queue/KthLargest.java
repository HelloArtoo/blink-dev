package com.gobue.blink.algo.queue;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * <pre>
 *     设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 *
 *     你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * </pre>
 */
public class KthLargest {

    final PriorityQueue<Integer> queue;
    final int max;

    public KthLargest(int k, int[] nums) {
        max = k;
        queue = new PriorityQueue<Integer>(k);
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (queue.size() < max) {
            queue.offer(val);
        } else if (queue.peek() < val) {
            queue.poll();
            queue.offer(val);
        }

        return queue.peek();
    }


    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        /**
         * 1、最小堆的方式
         */
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int num : nums) {
            if (heap.isEmpty() || heap.size() < k) {
                heap.offer(num);
                continue;
            }

            if (num > heap.peek()) {
                heap.poll();
                heap.offer(num);
            }
        }
        return heap.peek();
    }

    /**
     * 2、快排的方式取k大的值
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestQuickSort(int[] nums, int k) {
        return helper(nums, 0, nums.length - 1, k);
    }

    private static int helper(int[] nums, int l, int r, int k) {
        if (l >= r) {
            return -1;
        }
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
        if (i == k) {
            return pivot;
        } else if (i > k) {
            return helper(nums, l, i - 1, k);
        } else {
            return helper(nums, i + 1, r, k);
        }
    }

    /**
     * 3、快排方式取k大值2
     */
    private Random random = new Random();

    public int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        return findKth(nums, 0, len - 1, len - k);
    }

    private int findKth(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }

        //随机的方式选择pivot点
        int pivot = (l + random.nextInt(r - l));
        pivot = partition(nums, l, r, pivot);
        if (pivot == k) {
            return nums[pivot];
        } else if (pivot < k) {
            return findKth(nums, pivot + 1, r, k);
        } else {
            return findKth(nums, l, pivot - 1, k);
        }
    }

    private int partition(int[] nums, int l, int r, int pivot) {
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

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
//        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
//
//        System.out.println(kthLargest.add(3));
//        System.out.println(kthLargest.add(5));
//        System.out.println(kthLargest.add(10));
//        System.out.println(kthLargest.add(9));
//        System.out.println(kthLargest.add(4));

        int[] arr = {3, 2, 1, 5, 6, 4};
        //System.out.println(findKthLargest(arr, 2));
        System.out.println(findKthLargestQuickSort(arr, 2));


    }
}
