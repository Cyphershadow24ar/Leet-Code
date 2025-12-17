// PROBLEM : (3573) Best Time to Buy and Sell Stock V

// SOLUTION : 
class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if (n < 2 || k == 0) return 0;

        // dp[t][i] represents max profit with t transactions using days up to i
        long[][] dp = new long[k + 1][n];

        for (int t = 1; t <= k; t++) {
            // maxNormal tracks: dp[t-1][j-1] - prices[j]
            // maxShort tracks:  dp[t-1][j-1] + prices[j]
            // For j=0, dp[t-1][j-1] is effectively 0
            long maxNormal = -prices[0]; 
            long maxShort = prices[0];

            for (int i = 1; i < n; i++) {
                // 1. Carry forward the best profit from the previous day
                dp[t][i] = dp[t][i - 1];

                // 2. Update dp[t][i] using the precomputed max values
                // Current price + best previous "Buy" or -Current price + best previous "Sell"
                dp[t][i] = Math.max(dp[t][i], prices[i] + maxNormal);
                dp[t][i] = Math.max(dp[t][i], -prices[i] + maxShort);

                // 3. Update trackers for the NEXT day i+1
                // We must use j-1 to ensure we don't trade on the same day.
                // When considering day i as a potential start for a future transaction:
                if (i >= 1) {
                    long prevBase = dp[t - 1][i - 1];
                    maxNormal = Math.max(maxNormal, prevBase - prices[i + 1 > n - 1 ? i : i]); 
                    // To handle the "cannot trade on same day" rule correctly:
                    // A transaction ending on day i uses dp[t-1][i-2]
                    long baseProfit = (i >= 1) ? dp[t - 1][i - 1] : 0;
                    maxNormal = Math.max(maxNormal, baseProfit - prices[i]);
                    maxShort = Math.max(maxShort, baseProfit + prices[i]);
                }
            }
        }

        return dp[k][n - 1];
    }
}
