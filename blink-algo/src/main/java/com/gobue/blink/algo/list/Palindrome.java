package com.gobue.blink.algo.list;

import java.util.Stack;

public class Palindrome {

    public boolean isPalindrome(ListNode head) {
        Stack<Integer> s = new Stack<Integer>();
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            s.add(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null){
            slow = slow.next;
        }

        while(slow != null){
            int val = s.pop();
            if(slow.val != val){
                return false;
            }
            slow = slow.next;
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
