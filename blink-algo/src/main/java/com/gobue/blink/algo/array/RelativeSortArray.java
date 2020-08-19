package com.gobue.blink.algo.array;

/**
 * 给你两个数组，arr1 和 arr2，
 * <p>
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RelativeSortArray {

    /**
     * 计数排序
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] counts = new int[1001];
        for (int i = 0; i < arr1.length; i++) {
            counts[arr1[i]]++;
        }

        int[] rst = new int[arr1.length];
        int rstIndex = 0;
        //按照arr2排序结果
        for (int i = 0; i < arr2.length; i++) {
            while (counts[arr2[i]] > 0) {
                rst[rstIndex++] = arr2[i];
                counts[arr2[i]]--;
            }
        }
        //不在arr2中的，最后补位
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                rst[rstIndex++] = i;
                counts[i]--;
            }
        }
        return rst;
    }

}
