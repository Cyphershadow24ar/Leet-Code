// PROBLEM : (3397) Maximum Number of Distinct Elements After Operations

// SOLUTION : 

class Solution {
public:
    int maxDistinctElements(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int current = nums[0] - k;
        int count = 0;
        for (int num : nums) {
            int low = num - k;
            int high = num + k;
            int value = max(current, low);
            if (value <= high) {
                count++;
                current = value + 1;
            }
        }
        return count;
    }
};

