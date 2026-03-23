// PROBLEM : (1594) Maximum Non Negative Product in a Matrix

// SOLUTION :

class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long mod = 1_000_000_007;

        // dpMax[i][j] stores the maximum product to reach (i, j)
        // dpMin[i][j] stores the minimum product to reach (i, j)
        long[][] dpMax = new long[m][n];
        long[][] dpMin = new long[m][n];

        // Initialize the starting point
        dpMax[0][0] = dpMin[0][0] = grid[0][0];

        // Initialize the first column (only one way to move: down)
        for (int i = 1; i < m; i++) {
            dpMax[i][0] = dpMin[i][0] = dpMax[i - 1][0] * grid[i][0];
        }

        // Initialize the first row (only one way to move: right)
        for (int j = 1; j < n; j++) {
            dpMax[0][j] = dpMin[0][j] = dpMax[0][j - 1] * grid[0][j];
        }

        // Fill the DP tables
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                // Compare products from top and left
                long res1 = dpMax[i - 1][j] * val;
                long res2 = dpMin[i - 1][j] * val;
                long res3 = dpMax[i][j - 1] * val;
                long res4 = dpMin[i][j - 1] * val;

                dpMax[i][j] = Math.max(Math.max(res1, res2), Math.max(res3, res4));
                dpMin[i][j] = Math.min(Math.min(res1, res2), Math.min(res3, res4));
            }
        }

        long result = dpMax[m - 1][n - 1];
        return result < 0 ? -1 : (int) (result % mod);
    }
}
