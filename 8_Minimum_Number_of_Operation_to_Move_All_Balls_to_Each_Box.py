# PROBLEM: Minimum Number of Operations to Move All Balls to Each Box

# SOLUTION: 

class Solution(object):
    def minOperations(self, boxes):
        """
        :type boxes: str
        :rtype: List[int]
        """
        n = len(boxes)
        answer = [0] * n
        
        # Traverse from left to right
        count = 0  # Number of balls encountered
        operations = 0  # Cumulative operations to this point
        for i in range(n):
            answer[i] += operations
            count += int(boxes[i])
            operations += count
        
        # Traverse from right to left
        count = 0
        operations = 0
        for i in range(n - 1, -1, -1):
            answer[i] += operations
            count += int(boxes[i])
            operations += count
        
        return answer
