// PROBLEM : (1493) Longest Subarray of 1's After Deleting One Element

// SOLUTION :

class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int n = nums.size();
        int left = 0, zeros = 0, maxLen = 0;
        for(int right = 0; right <n; right++){
            if(nums[right] == 0) zeros++;

            while(zeros > 1){
                if(nums[left] == 0) zeros--;
                left++;
            }
            maxLen = max(maxLen, right-left + 1);
        }
        return maxLen-1;
    }
};
