// PROBLEM : (1674) Minimum Moves to Make Array Complementary

// SOLUTION :

class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] delta = new int[2 * limit + 2];
        int n = nums.length;
        
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            
            // Default: 2 moves for all possible sums
            delta[2] += 2;
            
            int minval = Math.min(a, b);
            int maxval = Math.max(a, b); // Fixed typo here
            
            // Range [minval + 1, maxval + limit] costs only 1 move
            delta[minval + 1] -= 1;
            
            // The specific sum (a + b) costs 0 moves
            delta[a + b] -= 1;
            delta[a + b + 1] += 1;
            
            // Beyond maxval + limit costs 2 moves again
            delta[maxval + limit + 1] += 1;
        }
        
        int minMoves = n;
        int currentMoves = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += delta[i];
            if (currentMoves < minMoves) {
                minMoves = currentMoves;
            }
        }
        
        return minMoves;
    }
}
