// PROBLEM :- (679) 24 Game

// SOLUTION :-

class Solution {
public:
    bool judgePoint24(vector<int>& cards) {
        vector<double> nums(cards.begin(), cards.end());
        return solve(nums);
    }

    bool solve(vector<double>& nums) {
        int n = nums.size();
        if (n == 1) {
            return fabs(nums[0] - 24) < 1e-6;  // check close to 24
        }

        // Try all pairs of numbers
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                vector<double> nextNums;
                // put all other numbers except i and j
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) nextNums.push_back(nums[k]);
                }

                // try all operations between nums[i] and nums[j]
                for (double val : compute(nums[i], nums[j])) {
                    nextNums.push_back(val);
                    if (solve(nextNums)) return true;
                    nextNums.pop_back();  // backtrack
                }
            }
        }
        return false;
    }

    // Generate all possible results of nums[i] and nums[j]
    vector<double> compute(double a, double b) {
        vector<double> results = {a + b, a - b, b - a, a * b};
        if (fabs(b) > 1e-6) results.push_back(a / b);
        if (fabs(a) > 1e-6) results.push_back(b / a);
        return results;
    }
};
