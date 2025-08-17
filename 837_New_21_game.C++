// PROBLEM :- (837) New 21 Game

// SOLUTION :- 
class Solution {
public:
    double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k - 1 + maxPts) return 1.0;

        vector<double> dp(n + 1, 0.0);
        dp[0] = 1.0;
        double windowSum = 1.0;  // sum of last maxPts dp values
        double result = 0.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = windowSum / maxPts;

            if (i < k) {
                windowSum += dp[i];  // can continue drawing
            } else {
                result += dp[i];     // stopping condition
            }

            if (i - maxPts >= 0) {
                windowSum -= dp[i - maxPts];
            }
        }

        return result;
    }
};
