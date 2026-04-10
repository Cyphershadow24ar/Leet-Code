// PROBLEM : (3740) Minimum Distance Between Three Equal Elements I

// SOLUTION :

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int[] firstSeen = new int[101];
        int[] secondSeen = new int[101];

        for (int i = 0; i <= 100; i++) {
            firstSeen[i] = -1;
            secondSeen[i] = -1;
        }
        
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            if (secondSeen[val] != -1) {
                int dist = 2 * (i - firstSeen[val]);
                minDistance = Math.min(minDistance, dist);
                firstSeen[val] = secondSeen[val];
                secondSeen[val] = i;
            } else if (firstSeen[val] != -1) {
                secondSeen[val] = i;
            } else {
                firstSeen[val] = i;
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
