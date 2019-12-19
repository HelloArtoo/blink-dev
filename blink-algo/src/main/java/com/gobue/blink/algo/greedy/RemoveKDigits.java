package com.gobue.blink.algo.greedy;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        if (num.length() == 1) {
            return "0";
        }
        int count = 0;
        //greedy algorithm
        char[] arr = num.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (count == k) {
                break;
            }
            int l = i, r = i;
            while (l >= 1 && arr[l - 1] > arr[r]) {
                if (arr[l - 1] == 'a') {
                    l--;
                    continue;
                }
                arr[l - 1] = arr[r];
                arr[r] = 'a';
                r = l - 1;
                count++;
                l--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != 'a') {
                sb.append(arr[j]);
            }
        }

        String str = sb.toString();
        while (str.startsWith("0")) {
            str = str.substring(1, str.length());
        }
        return "".equals(str) ? "0" : str;
    }


    public static void main(String[] args) {
        String s = "1432219";
        int k = 3;
        System.out.println(removeKdigits(s, k));
    }


}
