// PROBLEM : (3653) XOR After Range Multiplication Queries I

// SOLUTION :

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1000000007; // 10^9 + 7
        
        // Process each query
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];
            
            // Apply the multiplication operation according to the step k
            for (int idx = l; idx <= r; idx += k) {
                // Use a long to prevent overflow during multiplication
                long temp = (long) nums[idx] * v;
                nums[idx] = (int) (temp % MOD);
            }
        }
        
        // Calculate the bitwise XOR of the final array
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
}
