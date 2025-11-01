// PROBLEM : (3217) Delete Nodes From Linked List Present in Array

// SOLUTION :

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* modifiedList(vector<int>& nums, ListNode* head) {
        unordered_set<int> toDelete(nums.begin(), nums.end()); // O(n) space
        ListNode* dummy = new ListNode(0); // dummy head
        dummy->next = head;
        
        ListNode* prev = dummy;
        ListNode* curr = head;

        while (curr) {
            if (toDelete.count(curr->val)) {
                // delete node
                prev->next = curr->next;
            } else {
                prev = curr;
            }
            curr = curr->next;
        }

        return dummy->next;
    }
};
