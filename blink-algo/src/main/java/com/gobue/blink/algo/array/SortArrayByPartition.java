package com.gobue.blink.algo.array;

public class SortArrayByPartition {

    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * <p>
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * <p>
     * 你可以返回任何满足上述条件的数组作为答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int[] r = new int[A.length];
        int even = 0, odd = 1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                r[even] = A[i];
                even += 2;
            } else {
                r[odd] = A[i];
                odd += 2;
            }
        }
        return r;
    }

    //不需要额外的空间
    public int[] sortArrayByParityII2(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i+=2) {
            //找到偶数位的奇数
            if (A[i]%2 == 1) {
                //找到奇数位的偶数
                while(A[j]%2==1) {
                    j+=2;
                }
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }
}
