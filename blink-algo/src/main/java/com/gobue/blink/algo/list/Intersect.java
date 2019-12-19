package com.gobue.blink.algo.list;

public class Intersect {

    /**
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }

        ListNode fast = head.next.next, slow = head.next;
        while(fast != null && fast.next != null) {
            if(fast == slow) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast == null || fast.next == null) {
            return null;
        }

        while(head != fast) {
            head = head.next;
            fast = fast.next;
        }

        return head;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(0);
        n2.next = n3;
        ListNode n4 = new ListNode(-4);
        n3.next = n4;
        n4.next = n2;

        ListNode listNode = detectCycle(n1);
        System.out.println(listNode.val);
    }
}
