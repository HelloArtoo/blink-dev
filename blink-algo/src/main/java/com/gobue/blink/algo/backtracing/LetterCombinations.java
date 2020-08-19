package com.gobue.blink.algo.backtracing;

import java.util.*;

public class LetterCombinations {

    static int i  = 0;

    Map<Character, String> dict = new HashMap<>();

    {
        dict.put('2', "abc");
        dict.put('3', "def");
        dict.put('4', "ghi");
        dict.put('5', "jkl");
        dict.put('6', "mno");
        dict.put('7', "pqrs");
        dict.put('8', "tuv");
        dict.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> rst = new ArrayList<>();
        if (digits.length() == 1) {
            String s = dict.get(digits.charAt(digits.length() - 1));
            for(int i = 0; i < s.length(); i++) {
                rst.add(String.valueOf(s.charAt(i)));
            }

            return rst;
        }

        Character last = digits.charAt(digits.length() - 1);
        String val = dict.get(last);
        List<String> lst = letterCombinations(digits.substring(0, digits.length() - 1));
        for (int i = 0; i < lst.size(); i++) {
            for (int j = 0; j < val.length(); j++) {
                rst.add((lst.get(i) == null ? "" : lst.get(i)) + val.charAt(j));
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        String s = "23";
        List<String> strings = new LetterCombinations().letterCombinations(s);
        System.out.println(strings);

    }
}
