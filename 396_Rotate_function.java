// PROBLEM : (396) Rotate Function

// SOLUTION :

class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long f = 0;

        // Calculate S (total sum) and F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f += (long) i * nums[i];
        }

        long maxVal = f;

        // Iteratively calculate F(k) using the derived formula
        // F(k) = F(k-1) + sum - n * nums[n-k]
        for (int k = 1; k < n; k++) {
            f = f + sum - (long) n * nums[n - k];
            maxVal = Math.max(maxVal, f);
        }

        return (int) maxVal;
    }
}
