package com.gobue.blink.algo.scenario;


import java.util.Stack;

/**
 * 有一组不同高度的台阶，由一个整数数组表示，当开始下雨了台阶之间的水会积多少呢。
 * <p>
 * [0,1,0,2,1,0,1,3,2,1,2,1]
 */
public class RainStep {


    /**
     * area - stepArea = rainArea;
     * <p>
     * 横向计算总面积，用总体积 - 台阶体积 = 水的体积
     *
     * @param arr
     * @return
     */
    public static int getWater(int[] arr) {
        int min = 0, area = 0, stepArea = 0;
        int first = 0, end = arr.length - 1;
        while (first != end) {
            if (arr[first] == 0 || arr[first] <= min) {
                stepArea += arr[first];
                first++;
                continue;
            }

            if (arr[end] == 0 || arr[end] <= min) {
                stepArea += arr[end];
                end--;
                continue;
            }


            if (arr[first] > arr[end]) {
                stepArea += arr[end];
                area += (arr[end] - min) * (end - first + 1);
                min = arr[end];
                end--;
            } else if (arr[first] < arr[end]) {
                stepArea += arr[first];
                area += (arr[first] - min) * (end - first + 1);
                min = arr[first];
                first++;
            } else {
                stepArea += arr[first];
                stepArea += arr[end];
                area += (arr[first] - min) * (end - first + 1);
                min = arr[first];
                first++;
                end--;
            }
        }

        stepArea += arr[first];
        area += (arr[first] - min);
        return area - stepArea;
    }


    /**
     * 水的面积 =  整个面积 - 黑色面积 - 白色面积
     * <p>
     *
     * @param arr
     * @return
     */
    public static int getWater2(int[] arr) {

        int left = 0, right = arr.length - 1, leftMax = arr[0], rightMax = arr[arr.length - 1], volumn = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                if (arr[++left] >= leftMax) {
                    leftMax = arr[left];
                } else {
                    volumn += (leftMax - arr[left]);
                }
            } else {
                if (arr[--right] >= rightMax) {
                    rightMax = arr[right];
                } else {
                    volumn += (rightMax - arr[right]);
                }
            }
        }

        return volumn;
    }

    /**
     * 方式3：单调栈的方式
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if(height == null || height.length <= 2) {
            return 0;
        }

        int volumn = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            //单调栈,柱子递减直到遇上打破递减的柱子
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                Integer h = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                //高度 * 宽度 = 面积
                volumn += Math.min(height[i] - height[h], height[stack.peek()] - height[h]) * (i - 1 - stack.peek());
            }
            stack.push(i);
        }
        return volumn;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(getWater(arr));
        System.out.println(getWater2(arr));
        System.out.println(trap(arr));
    }
}
