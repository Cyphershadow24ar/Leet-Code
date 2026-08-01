# PROBLEM : (486) Predict the Winner

# SOLUTION :

class Solution:
    def predictTheWinner(self, nums: list[int]) -> bool:
        n = len(nums)
        # dp[i] will store the maximum score difference a player can get 
        # from the subarray starting at index i
        dp = nums[:]
        
        # Build the table for subarrays of length 2 up to n
        for length in range(2, n + 1):
            for i in range(n - length + 1):
                j = i + length - 1
                
                # The player can either pick the left element (nums[i]) 
                # or the right element (nums[j]).
                # We subtract the opponent's best possible score difference 
                # from the remaining subarray.
                pick_left = nums[i] - dp[i + 1]
                pick_right = nums[j] - dp[i]
                
                dp[i] = max(pick_left, pick_right)
                
        # If the final score difference for the whole array is >= 0, Player 1 wins
        return dp[0] >= 0
