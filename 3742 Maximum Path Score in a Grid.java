// PROBLEM : (3742) Maximum Path Score in a Grid

// SOLUTION :

import java.util.Arrays;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j][cost] stores the max score at (i, j) with a specific cost
        int[][][] dp = new int[m][n][k + 1];

        // Initialize with -1 to represent unreachable states
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // Base case: starting point (0, 0)
        // Constraints say grid[0][0] is always 0, so cost is 0 and score is 0.
        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    if (dp[i][j][c] == -1) continue;

                    // Try moving Down or Right
                    int[][] directions = {{0, 1}, {1, 0}};
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];

                        if (ni < m && nj < n) {
                            int val = grid[ni][nj];
                            int nextCost = c + (val > 0 ? 1 : 0);
                            int nextScore = dp[i][j][c] + (val == 2 ? 2 : (val == 1 ? 1 : 0));

                            if (nextCost <= k) {
                                dp[ni][nj][nextCost] = Math.max(dp[ni][nj][nextCost], nextScore);
                            }
                        }
                    }
                }
            }
        }

        // Find the maximum score at (m-1, n-1) across all valid costs <= k
        int maxScore = -1;
        for (int c = 0; c <= k; c++) {
            maxScore = Math.max(maxScore, dp[m - 1][n - 1][c]);
        }

        return maxScore;
    }
}
