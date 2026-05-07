// PROBLEM : (3660) Jump Game IX

// SOLUTOIN :

class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        if (n == 0) return new int[0];
        
        // Step 1: Compute prefix maximums
        int[] prefixMax = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }
        
        // Step 2: Compute suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        int[] ans = new int[n];
        int start = 0;
        
        // Step 3: Find chunk boundaries and assign the maximum reachable value
        for (int i = 0; i < n; i++) {
            // A boundary is found when the max of the left part 
            // is less than or equal to the min of the right part.
            if (i == n - 1 || prefixMax[i] <= suffixMin[i + 1]) {
                
                // The maximum value in this connected chunk
                int currentMax = prefixMax[i];
                
                // Assign this max value to all elements in the chunk
                for (int j = start; j <= i; j++) {
                    ans[j] = currentMax;
                }
                
                // Move the start pointer for the next chunk
                start = i + 1;
            }
        }
        
        return ans;
    }
}
