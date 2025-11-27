// PROBLEM : (3381) Maximum subarray sum with length Divisible by K

// SOLUTION :

class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;

        long[] best = new long[k];
        Arrays.fill(best, Long.MAX_VALUE); 

        long prefix = 0;
        best[0] = 0; // prefix[0] = 0

        long ans = Long.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            prefix += nums[i - 1];

            int rem = i % k;

            if (best[rem] != Long.MAX_VALUE) {
                ans = Math.max(ans, prefix - best[rem]);
            }

            // update best prefix for this remainder
            best[rem] = Math.min(best[rem], prefix);
        }

        return ans;
    }
}
