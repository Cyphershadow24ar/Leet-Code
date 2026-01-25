// PROBLEM : (1984) Minimum Difference Between Highest and Lowest of K Scores

// SOLUTION :

import java.util.Arrays;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        // If only one student is chosen, difference is always 0
        if (k == 1) {
            return 0;
        }

        // Sort the scores
        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;

        // Sliding window of size k
        for (int i = 0; i + k - 1 < nums.length; i++) {
            int diff = nums[i + k - 1] - nums[i];
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }
}
