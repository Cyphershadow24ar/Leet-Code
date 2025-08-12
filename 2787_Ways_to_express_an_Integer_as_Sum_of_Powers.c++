// PROBLEM : (2787) Ways to Express an Integer as Sum of Powers

// SOLUTION :-

class Solution {
public:
    const int MOD = 1e9 + 7;
    vector<vector<int>> dp;

    int solve(int curr, int target, int x) {
        if (target == 0) return 1;
        if ((int)pow(curr, x) > target) return 0;

        if (dp[curr][target] != -1) return dp[curr][target];

        int take = 0;
        int powerVal = (int)pow(curr, x);
        if (powerVal <= target)
            take = solve(curr + 1, target - powerVal, x);

        int skip = solve(curr + 1, target, x);

        return dp[curr][target] = (take + skip) % MOD;
    }

    int numberOfWays(int n, int x) {
        dp.assign(n + 2, vector<int>(n + 1, -1));
        return solve(1, n, x);
    }
};
