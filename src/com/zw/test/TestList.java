package com.zw.test;

public class TestList {


    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        ListNode head = createList1(arr.length);

        ListNode p = head.next;
        int key = 10;
        while (p != null) {
            p.data = key;
            key++;
            p = p.next;
        }

        p = head.next;
        while (p != null) {
            System.out.println(p.data);
            p = p.next;
        }
    }

    /**
     * 头插法建立链表
     *
     * @param length
     * @return
     */
    private static ListNode createList(int length) {
        ListNode head = new ListNode();

        for (int i = 0; i < length; i++) {
            ListNode node = new ListNode();
            node.next = head.next;
            head.next = node;
        }
        return head;
    }

    /**
     * 尾插法建立链表
     *
     * @param length
     * @return
     */
    public static ListNode createList1(int length) {
        ListNode head = new ListNode();

        ListNode pre = head;

        for (int i = 0; i < length; i++) {
            ListNode node = new ListNode();
            node.next = pre.next;
            pre.next = node;
            pre = node;
        }
        return head;
    }
}
