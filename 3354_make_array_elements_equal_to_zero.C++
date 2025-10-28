// Problem : (3354) Make Array Elements Equal to Zero

// Solution :

#include <vector>
using namespace std;

class Solution {
public:
    bool simulate(vector<int> nums, int curr, int dir) {
        int n = nums.size();
        while (curr >= 0 && curr < n) {
            if (nums[curr] == 0) {
                curr += dir;
            } else {
                nums[curr]--;
                dir = -dir;  // reverse direction
                curr += dir;
            }
        }
        for (int x : nums)
            if (x != 0) return false;
        return true;
    }

    int countValidSelections(vector<int>& nums) {
        int n = nums.size();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (simulate(nums, i, 1)) count++;   // right
                if (simulate(nums, i, -1)) count++;  // left
            }
        }
        return count;
    }
};
