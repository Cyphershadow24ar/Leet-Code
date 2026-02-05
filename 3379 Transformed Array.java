// PROBLEM : (3379) Transformed Array

// SOLUTION :

class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            // Calculate the target index using the circular formula
            // We add n before the final modulo to handle negative results from (i + nums[i]) % n
            int targetIndex = ((i + nums[i]) % n + n) % n;
            
            result[i] = nums[targetIndex];
        }

        return result;
    }
}
