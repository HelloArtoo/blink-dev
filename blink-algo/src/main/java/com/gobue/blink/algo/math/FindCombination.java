package com.gobue.blink.algo.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FindCombination {


    /**
     * 给出奖金票面价值，给出所有组合形式
     *
     * @param reward 奖金
     * @param moneys 票面价值
     */
    public static void findWays(int reward, int[] moneys, ArrayList<Integer> results) {
        if (reward == 0) {
            System.out.println(results);
        } else if (reward < 0) {
            return;
        } else {
            for (int i = 0; i < moneys.length; i++) {
                ArrayList<Integer> result = (ArrayList<Integer>) results.clone();
                result.add(moneys[i]);
                findWays(reward - moneys[i], moneys, result);
            }
        }
    }

    /**
     * 给定一个整数，找到所有可能的分解.
     * 例如：输入8，可能是1X8,8X1,2X4,4X2,1X2X2X2,1X2X4 ....
     *
     * @param num
     */
    public static void multipleWays(int num) {
        printMultipleWays(num, 0, new ArrayList<Integer>());
    }

    private static Set<Integer> sets = new HashSet<Integer>();

    private static void printMultipleWays(int num, int left, ArrayList<Integer> results) {
        if (num == 1 && left == 0) {
            System.out.println(results);
        } else if (left != 0) { //不能整除
            return;
        } else {
            for (int i = 1; i <= num; i++) {
                if (results.contains(1) && i == 1) {
                    continue;
                }
                ArrayList<Integer> result = (ArrayList<Integer>) results.clone();
                result.add(i);
                printMultipleWays(num / i, num % i, result);
            }

        }
    }

    public static void main(String[] args) {
        multipleWays(8);
    }

}
