// PROBLEM : (876) Middle of the Linked List

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
    public ListNode middleNode(ListNode head) {
        ListNode temp=head;
        int count=0;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        int mid=count/2;
        temp=head;

        for(int i=0;i<mid;i++){
            temp=temp.next;
        }
        return temp;
    }
}
