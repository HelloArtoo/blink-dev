package com.gobue.blink.algo.string;

public class IsPalindrome {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        s = s.toLowerCase();

        int end = 0;
        char[] sarr = s.toCharArray();
        for (int i = 0; i < sarr.length; i++) {
            if (!Character.isLetterOrDigit(sarr[i])) {
                continue;
            }
            sarr[end++] = sarr[i];
        }
        end--;
        int start = 0;
        while (start < end) {
            if (sarr[start] != sarr[end]) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static boolean isPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        int left = 0, right = s.length() - 1;
        while(true) {
            while(left <= right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while(left <= right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if(left >= right) {
                break;
            }

            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {

        //System.out.println(isPalindrome("0P"));
        System.out.println(isPalindrome2("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome2(".,"));


    }
}
