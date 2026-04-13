// PROBLEM : (1848) Minimum Distance to the Target Element

// SOLUTION :

class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int mindist = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            if(nums[i] == target){
                int dist = Math.abs(i- start);
                mindist = Math.min(mindist, dist);
            }
        }
        return mindist;
    }
}
