package com.gobue.blink.algo.math;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 */
public class ReverseInteger {

    public static int reverse(int x) {
        int rst = 0;
        while(x != 0) {
            int remain = x%10;
            x = x/10;
            if((rst > Integer.MAX_VALUE/10 || (rst==Integer.MAX_VALUE/10 && remain>7)) ||
                    (rst < Integer.MIN_VALUE/10 || (rst==Integer.MIN_VALUE/10 && remain<-8))) {
                return 0;
            }
            rst = rst*10 +remain;
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1563847412));

    }
}
