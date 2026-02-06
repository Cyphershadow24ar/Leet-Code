// PROBLEM : (3634) Minimum Removals to Balance Array

// SOLUITON :

class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int l =0;
        int maxLen = 1;

        for(int r=0; r<n; r++){
            while((long) nums[r] > (long) nums[l]*k){
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
        }
        return n-maxLen;
    }
}
