// PROBLEM : (3652) Best Time to Buy and Sell Stock using Strategy

// SOLUTION :

class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        // 1. Base profit
        long baseProfit = 0;
        for (int i = 0; i < n; i++) {
            baseProfit += (long) strategy[i] * prices[i];
        }

        // 2. Prefix sums
        long[] profitPS = new long[n + 1]; // strategy[i] * prices[i]
        long[] pricePS = new long[n + 1];  // prices[i]

        for (int i = 0; i < n; i++) {
            profitPS[i + 1] = profitPS[i] + (long) strategy[i] * prices[i];
            pricePS[i + 1] = pricePS[i] + prices[i];
        }

        long maxGain = 0;
        int half = k / 2;

        // 3. Sliding window
        for (int l = 0; l + k <= n; l++) {
            int mid = l + half;
            int r = l + k;

            // Left half loss
            long leftLoss = profitPS[mid] - profitPS[l];

            // Right half gain
            long rightGain = (pricePS[r] - pricePS[mid]) 
                           - (profitPS[r] - profitPS[mid]);

            long totalGain = -leftLoss + rightGain;
            maxGain = Math.max(maxGain, totalGain);
        }

        return baseProfit + maxGain;
    }
}
