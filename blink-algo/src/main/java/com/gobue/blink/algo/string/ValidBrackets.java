package com.gobue.blink.algo.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidBrackets {


    /**
     * '('，')'，'{'，'}'，'['，']'
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<Character, Character>(3);
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //右括号
            if (map.containsKey(c)) {
                // c != stack.pop();
                if (stack.isEmpty() || !map.get(c).equals(stack.pop())) {
                    return false;
                }
            } else {//左括号
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }
}
