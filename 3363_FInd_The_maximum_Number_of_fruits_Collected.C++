// PROBLEM :- (3363) Find the Maximum Number of Fruits Collected

// SOLUTION :-

class Solution {
public:
    int maxCollectedFruits(vector<vector<int>>& fruits) {
        int n = fruits.size();
        int ans = 0;

        // Collect diagonal path fruits for child A
        for (int i = 0; i < n; ++i) {
            ans += fruits[i][i];
            fruits[i][i] = 0;  // prevent re-use by B/C
        }

        // Lambda DP function
        auto dp = [&]() -> int {
            vector<int> prev(n, INT_MIN), curr(n, INT_MIN);
            prev[n - 1] = fruits[0][n - 1];
            for (int i = 1; i < n - 1; ++i) {
                for (int j = max(n - 1 - i, i + 1); j < n; ++j) {
                    int best = prev[j];
                    if (j - 1 >= 0) best = max(best, prev[j - 1]);
                    if (j + 1 < n) best = max(best, prev[j + 1]);
                    curr[j] = best + fruits[i][j];
                }
                swap(prev, curr);
            }
            return prev[n - 1];
        };

        // Child B: from (0, n-1)
        ans += dp();

        // Transpose grid for child C: from (n-1, 0)
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                swap(fruits[j][i], fruits[i][j]);
            }
        }

        // Child C
        ans += dp();

        return ans;
    }
};
