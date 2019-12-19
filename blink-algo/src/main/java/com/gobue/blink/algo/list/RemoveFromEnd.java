package com.gobue.blink.algo.list;

public class RemoveFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) {
            return head;
        }

        int len = 0;
        ListNode l = head;
        while(l != null) {
            len++;
            l = l.next;
        }

        int delIndex = len - n;
        int index = 0;
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode orignHead = newHead;
        while(newHead != null) {
            if(index++ == delIndex) {
                newHead.next = newHead.next.next;
            }
            newHead = newHead.next;
        }

        return orignHead.next;

    }

    public static void main(String[] args) {


        ListNode.print(removeNthFromEnd(ListNode.build(new int[]{1}), 1));
    }
}
