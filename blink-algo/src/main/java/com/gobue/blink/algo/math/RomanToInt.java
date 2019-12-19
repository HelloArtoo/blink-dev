package com.gobue.blink.algo.math;

import java.util.*;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RomanToInt {

    static Map<String, Integer> dict = new HashMap<>();

    static {
        dict.put("I", 1);
        dict.put("V", 5);
        dict.put("X", 10);
        dict.put("L", 50);
        dict.put("C", 100);
        dict.put("D", 500);
        dict.put("M", 1000);

        dict.put("IV", 4);
        dict.put("IX", 9);
        dict.put("XL", 40);
        dict.put("XC", 90);
        dict.put("CD", 400);
        dict.put("CM", 900);
    }

    /**
     * 代码清晰了，但是性能并不好
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }

        int rst = dict.get(s.substring(0, 1));
        for (int i = 1; i < s.length(); i++) {
            Integer two = dict.get(s.substring(i - 1, i + 1));
            Integer one = dict.get(s.substring(i, i + 1));
            if (two != null) {
                rst -= dict.get(s.substring(i - 1, i));
                rst += two;
            } else {
                rst += one;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
