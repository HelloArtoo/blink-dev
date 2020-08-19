package com.gobue.blink.algo.list;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {

    int val;
    ListNode next;

    public ListNode(int value) {
        this.val = value;
    }

    public static void print(ListNode head) {
        System.out.print("");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode build(int[] arr) {
        ListNode listNode = new ListNode(-1);
        ListNode tmp = listNode;
        for (int i = 0; i < arr.length; i++) {
            tmp.next = new ListNode(arr[i]);
            tmp = tmp.next;
        }

        ListNode rst = listNode.next;
        print(rst);

        return rst;
    }
}
