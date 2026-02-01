// PROBLEM : (3010) Divide an Array Into Subarrays With Minimum Cost I

// SOLUTION :

import java.util.Arrays;

class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
        
        // The first element is mandatory as the start of the first subarray
        int firstValue = nums[0];
        
        // We need to find the two smallest elements in the rest of the array
        // Copy the remaining elements into a new array to sort them
        int[] remaining = new int[n - 1];
        for (int i = 1; i < n; i++) {
            remaining[i - 1] = nums[i];
        }
        
        // Sort the remaining elements
        Arrays.sort(remaining);
        
        // The minimum cost is the first element + the two smallest remaining
        return firstValue + remaining[0] + remaining[1];
    }
}
