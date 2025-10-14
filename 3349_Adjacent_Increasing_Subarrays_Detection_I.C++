// PROBLEM : (3349) Adjacent Increasing Subarrays Detection I

// SOLUTION :

class Solution {
public:
    bool hasIncreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        for (int a = 0; a <= n - 2 * k; a++) {
            bool first = true;
            for (int j = a; j < a + k - 1; j++) {
                if (nums[j] >= nums[j + 1]) {
                    first = false;
                    break;
                }
            }
            if (!first) continue;
            
            bool second = true;
            for (int j = a + k; j < a + 2 * k - 1; j++) {
                if (nums[j] >= nums[j + 1]) {
                    second = false;
                    break;
                }
            }
            if (second) return true;
        }
        return false;
    }
};
