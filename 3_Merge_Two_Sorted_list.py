# PROBLEM: Merge Two Sorted List

# SOLUTION :

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def mergeTwoLists(self, list1, list2):
        """
        :type list1: Optional[ListNode]
        :type list2: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
         # Create a dummy node to act as the start of the merged list
        merge_list = ListNode(-1)
        current = merge_list
        
        # Traverse both lists, picking the smaller node each time
        while list1 and list2:
            if list1.val <= list2.val:
                current.next = list1
                list1 = list1.next
            else:
                current.next = list2
                list2 = list2.next
            current = current.next
        
        # If any elements are left in either list, append them
        if list1:
            current.next = list1
        elif list2:
            current.next = list2
        
        # Return the merged list, skipping the dummy node
        return merge_list.next
