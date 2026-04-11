// PROBLEM : (3741) Minimum Distance Between Three Equal Elements II

// SOLUTION :

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        
        // Arrays to store the last two seen indices for each number.
        // We use n + 1 because the constraints say 1 <= nums[i] <= n.
        int[] prev1 = new int[n + 1];
        int[] prev2 = new int[n + 1];
        
        // Initialize arrays with -1 to indicate no index has been recorded yet.
        for (int i = 0; i <= n; i++) {
            prev1[i] = -1;
            prev2[i] = -1;
        }
        
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            // If we have at least two previous occurrences, we found a valid triplet.
            if (prev2[val] != -1) {
                int dist = 2 * (i - prev2[val]);
                minDistance = Math.min(minDistance, dist);
            }
            
            // Shift history: the most recent becomes the second most recent, 
            // and the current index becomes the new most recent.
            prev2[val] = prev1[val];
            prev1[val] = i;
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
