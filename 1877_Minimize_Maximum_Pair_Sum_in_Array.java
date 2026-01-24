// PROBLEM : (1877) Minimize Maximum Pair Sum in Array

// SOLUTION :

import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        // 1. Sort the array to bring smallest and largest values to the ends
        Arrays.sort(nums);
        
        int maxPairSum = 0;
        int n = nums.length;
        
        // 2. Use two pointers (or a single loop with symmetric indices)
        // to pair the i-th smallest with the i-th largest
        for (int i = 0; i < n / 2; i++) {
            int currentPairSum = nums[i] + nums[n - 1 - i];
            
            // 3. Keep track of the highest sum we've seen
            maxPairSum = Math.max(maxPairSum, currentPairSum);
        }
        
        return maxPairSum;
    }
}
