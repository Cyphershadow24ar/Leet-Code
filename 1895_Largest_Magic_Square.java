// PROBLEM : (1895) Largest Magic Square

// SOLUTION :

class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Prefix sums for rows and columns
        int[][] rows = new int[m][n + 1];
        int[][] cols = new int[n][m + 1];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i][j + 1] = rows[i][j] + grid[i][j];
                cols[j][i + 1] = cols[j][i] + grid[i][j];
            }
        }
        
        // Check squares from largest possible size k down to 2
        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (isMagic(grid, i, j, k, rows, cols)) {
                        return k;
                    }
                }
            }
        }
        
        return 1;
    }
    
    private boolean isMagic(int[][] grid, int r, int c, int k, int[][] rows, int[][] cols) {
        // Use the first row sum as the target sum
        int target = rows[r][c + k] - rows[r][c];
        
        // Check rows
        for (int i = r + 1; i < r + k; i++) {
            if (rows[i][c + k] - rows[i][c] != target) return false;
        }
        
        // Check columns
        for (int j = c; j < c + k; j++) {
            if (cols[j][r + k] - cols[j][r] != target) return false;
        }
        
        // Check main diagonal
        int diag1 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
        }
        if (diag1 != target) return false;
        
        // Check anti-diagonal
        int diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag2 += grid[r + i][c + k - 1 - i];
        }
        if (diag2 != target) return false;
        
        return true;
    }
}
