// PROBLEM : (1594) Maximum Non Negative Product in a Matrix

// SOLUTION :

class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long mod = 1_000_000_007;

        long[][] dpMax = new long[m][n];
        long[][] dpMin = new long[m][n];

        dpMax[0][0] = dpMin[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dpMax[i][0] = dpMin[i][0] = dpMax[i - 1][0] * grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dpMax[0][j] = dpMin[0][j] = dpMax[0][j - 1] * grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                
                long a = dpMax[i - 1][j] * val;
                long b = dpMin[i - 1][j] * val;
                long c = dpMax[i][j - 1] * val;
                long d = dpMin[i][j - 1] * val;

                dpMax[i][j] = Math.max(Math.max(a, b), Math.max(c, d));
                dpMin[i][j] = Math.min(Math.min(a, b), Math.min(c, d));
            }
        }

        long finalResult = dpMax[m - 1][n - 1];
        
        return finalResult < 0 ? -1 : (int) (finalResult % mod);
    }
}
