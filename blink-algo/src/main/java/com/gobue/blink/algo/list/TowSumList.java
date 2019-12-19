package com.gobue.blink.algo.list;

public class TowSumList {

    /**
     * 链表进位
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int i = l1 != null ? l1.val : 0;
            int j = l2 != null ? l2.val : 0;
            int result = i + j + carry;
            carry = result / 10;
            cur.next = new ListNode(result % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry == 1) {
            cur.next = new ListNode(1);
        }

        return head.next;

    }


    /**
     * 超出最大长度
     *
     * @param l1
     * @param l2
     * @return
     */
    @Deprecated()
    public static ListNode addTwoNumbersNotRight(ListNode l1, ListNode l2) {
        long num1 = 0, num2 = 0, i = 0, j = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                num1 += l1.val * Math.pow(10, i++);
                num2 += l2.val * Math.pow(10, j++);
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                num1 += l1.val * Math.pow(10, i++);
                l1 = l1.next;
            } else {
                num2 += l2.val * Math.pow(10, j++);
                l2 = l2.next;
            }
        }

        long rst = num1 + num2;
        if (rst == 0) {
            return new ListNode(0);
        }

        ListNode result = new ListNode(-1);
        ListNode lst = result;
        while (rst != 0) {
            lst.next = new ListNode((int) (rst % 10));
            lst = lst.next;
            rst = rst / 10;
        }
        return result.next;
    }


    public static void main(String[] args) {
        ListNode l1 = ListNode.build(new int[]{5, 6, 4});
        ListNode l2 = ListNode.build(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});

        System.out.println("最终结果：");
        ListNode.print(addTwoNumbers(l1, l2));
    }
}

