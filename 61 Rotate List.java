// PROBLEM : (61) Rotate List

// SOLUTION :

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Base cases: empty list, single node, or no rotation
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Step 1: Find the length of the list and the tail node
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // Step 2: Calculate the effective rotations needed
        k = k % length;
        if (k == 0) {
            return head; // No change needed if k is a multiple of length
        }
        
        // Step 3: Connect tail to head to form a circular linked list
        tail.next = head;
        
        // Step 4: Find the new tail (which is length - k - 1 steps from current head)
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        
        // Step 5: Set the new head and break the circular connection
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
}
