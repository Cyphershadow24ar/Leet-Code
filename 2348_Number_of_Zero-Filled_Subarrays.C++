// PROBLEM :- (2348) Number of Zero-Filled Subarrays

// SOLUTION :-

class Solution {
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        long long ans = 0, count = 0;

        for(int num : nums){
            if(num == 0){
                count++;
                ans += count;
            } else{
                count = 0;
            }
        }
        return ans;
    }
};
