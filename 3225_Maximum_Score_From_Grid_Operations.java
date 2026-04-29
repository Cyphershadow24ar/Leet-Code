// PROBLEM : (3225) Maximum Score From Grid Operations

// SOLUTION :

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] pref = new long[n + 1][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[i + 1][j] = pref[i][j] + (long)grid[i][j];
            }
        }

        long[][] dp = new long[n + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            for (int k = 1; k <= n; k++) {
                dp[j][k] = -1_000_000_000_000_000L;
            }
        }

        for (int i = 0; i < n; i++) {
            long[][] nextDp = new long[n + 1][n + 1];
            for (int j = 0; j <= n; j++) {
                long[] maxPrefix = new long[n + 1];
                long curMaxP = -2_000_000_000_000_000L;
                for (int k = 0; k <= n; k++) {
                    curMaxP = Math.max(curMaxP, dp[j][k]);
                    maxPrefix[k] = curMaxP;
                }
                
                long[] maxSuffix = new long[n + 1];
                long curMaxS = -2_000_000_000_000_000L;
                for (int k = n; k >= 0; k--) {
                    curMaxS = Math.max(curMaxS, dp[j][k] + pref[k][i]);
                    maxSuffix[k] = curMaxS;
                }
                
                for (int nj = 0; nj <= n; nj++) {
                    int M = Math.max(j, nj);
                    long bestK = maxPrefix[M] + pref[M][i];
                    if (M + 1 <= n) {
                        bestK = Math.max(bestK, maxSuffix[M + 1]);
                    }
                    nextDp[nj][j] = bestK - pref[j][i];
                }
            }
            dp = nextDp;
        }

        long ans = 0;
        for (int k = 0; k <= n; k++) {
            ans = Math.max(ans, dp[0][k]);
        }
        return ans;
    }
}
