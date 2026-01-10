// PROBLEM : (712) Minimum ASCII Delete Sum for Two Strings

// SOLUTION : 

class Solution {
public:
    int minimumDeleteSum(string s1, string s2) {
        int n = s1.size(), m = s2.size();
        vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));

        // Base cases
        for (int i = n - 1; i >= 0; i--)
            dp[i][m] = dp[i + 1][m] + s1[i];

        for (int j = m - 1; j >= 0; j--)
            dp[n][j] = dp[n][j + 1] + s2[j];

        // Fill DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = min(
                        s1[i] + dp[i + 1][j],
                        s2[j] + dp[i][j + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }
};
