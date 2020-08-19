package com.gobue.blink.algo.list;

public class InsertionSortList {

    /**
     * 4ms
     * 对链表进行插入排序。
     * <p>
     * <p>
     * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/insertion-sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dumb = new ListNode(Integer.MIN_VALUE);
        ListNode newHead = dumb, tail = dumb;
        while (head != null) {
            ListNode next = head.next;
            //判断是否比尾部还大
            if (tail.val < head.val) {
                tail.next = head;
                tail = head;
                tail.next = null;
            } else {
                ListNode cur = newHead;
                //找到插入点
                while (cur.next != null && cur.next.val <= head.val) {
                    cur = cur.next;
                }
                ListNode curNext = cur.next;
                cur.next = head;
                head.next = curNext;
            }
            head = next;
        }
        return dumb.next;
    }

    public ListNode insertionSortList2(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode newHead = head;
        head = head.next;
        newHead.next = null;
        while (head != null) {
            ListNode next = head.next;
            if (head.val < newHead.val) {
                head.next = newHead;
                newHead = head;
            } else {
                ListNode cur = newHead;
                while (cur.next != null && cur.next.val <= head.val) {
                    cur = cur.next;
                }

                ListNode curNext = cur.next;
                cur.next = head;
                head.next = curNext;
            }
            head = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.build(new int[]{4, 2, 1, 3});
        ListNode l2 = ListNode.build(new int[]{1, 1});
        ListNode.print(new InsertionSortList().insertionSortList(l1));
    }
}
