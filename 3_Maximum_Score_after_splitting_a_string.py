# PROBLEM : Maximum score after splitting a string

# SOLUTION: 

class Solution(object):
    def maxScore(self, s):
        """
        :type s: str
        :rtype: int
        """
        maxScore = 0
        n = len(s)
        for i in range(1, n):
            # Calculate the score
            left_zeros = s[:i].count('0')
            right_ones = s[i:].count('1')
            score = left_zeros + right_ones

            # Update the maximum score
            maxScore = max(maxScore, score)

        return maxScore
