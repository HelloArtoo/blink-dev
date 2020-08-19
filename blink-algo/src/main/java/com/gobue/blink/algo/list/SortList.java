package com.gobue.blink.algo.list;

/**
 * 排序链表
 */
public class SortList {

    /**
     * 链表归并排序
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //找到分区点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //断链
        ListNode fastHead = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(fastHead);
        //合并有序链表
        return mergeSortedList(left, right);
    }

    /**
     * 合并两个有序列表
     *
     * @param left
     * @param right
     * @return
     */
    private ListNode mergeSortedList(ListNode left, ListNode right) {
        ListNode dumb = new ListNode(-1);
        ListNode pre = dumb;
        while (left != null && right != null) {
            if (left.val > right.val) {
                pre.next = right;
                right = right.next;
            } else {
                pre.next = left;
                left = left.next;
            }
            pre = pre.next;
        }
        pre.next = (left == null ? right : left);
        return dumb.next;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.build(new int[]{4, 2, 1, 3});
        ListNode.print(new SortList().sortList(l1));
    }
}
