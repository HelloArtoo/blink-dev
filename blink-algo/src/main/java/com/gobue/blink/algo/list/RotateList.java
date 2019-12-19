package com.gobue.blink.algo.list;

public class RotateList {

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }

        //计算长度，形成环
        int len = 1;
        ListNode tmp = head;
        for(;tmp.next != null; len++) {
            tmp = tmp.next;
        }
        tmp.next = head;

        //断开环，返回新的头
        ListNode newTail = head;
        for(int i = 0; i < len - (k%len) -1; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.build(new int[]{1});
        ListNode.print(rotateRight(list, 1));

    }

}
