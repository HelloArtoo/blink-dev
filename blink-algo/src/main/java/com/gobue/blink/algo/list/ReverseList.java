package com.gobue.blink.algo.list;

public class ReverseList {

    /**
     * 反转链表
     */
    public void reverse() {

    }


    /**
     * 几个指针
     * <pre>
     *     1、cur，遍历指针，从到走到尾
     *     2、head指针，新链表的头指针（当m=1的时候，head指针需要变）
     *     3、
     *
     * </pre>
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) {
            return head;
        }

        ListNode bef = null;
        ListNode pre = null;
        ListNode cur = head;
        int index = 0;
        while (cur != null) {
            if ((index + 1) == m) {
                ListNode tail = cur;
                while (cur != null && index++ != n) {
                    ListNode tmp = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = tmp;
                }

                tail.next = cur;
                cur = null;
            } else {
                index++;
                bef = cur;
                cur = cur.next;
            }
        }

        if (bef != null) {
            bef.next = pre;
            return head;
        } else {
            return pre;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode.print(reverseBetween(node1, 1, 4));

    }
}
