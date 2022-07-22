package com.popov.test_tasks_challenge.misc.trees;

/**
 * Revert Linked list;
 * Given a point to head of linked list;
 * One needs to revert in so that head becomes a tail, etc.
 * [1, 2, 3, 4, 5] ->[5, 4, 3, 2, 1]
 * @see <a href ="https://www.geeksforgeeks.org/reverse-a-linked-list/">Link</a>
 */
public class RevertLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null;

        System.out.println("Init list = "+ head);
        ListNode newHead = new RevertLinkedList().revert(head);
        System.out.println("Result list = "+ newHead);
    }

    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode curr = this;
            StringBuilder stringBuilder = new StringBuilder();
            while (curr!=null) {
                stringBuilder.append(curr.val+ (curr.next!=null ? ", ":  ""));
                curr = curr.next;
            }
            return stringBuilder.toString();
        }
    }

    private ListNode revert(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;

        while (curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev= curr;
            curr = next;
        }
        return prev;
    }
}
