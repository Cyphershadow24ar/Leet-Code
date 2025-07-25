// PROBLEM :-  (3487) Maximum Unique Subarray Sum After Deletion

// SOLUTION :-

class Solution {
public:
    int maxSum(vector<int>& nums) {
        unordered_set<int> uniqueNums(nums.begin(), nums.end());
        int maxElement = INT_MIN;
        int sumPositives = 0;
        
        for (int num : uniqueNums) {
            maxElement = max(maxElement, num);
            if (num > 0) sumPositives += num;
        }
        
        return sumPositives > 0 ? sumPositives : maxElement;
    }
};
