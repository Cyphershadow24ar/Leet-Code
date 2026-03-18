// PROBLEM : (3070) Count Submatrices with Top-Left Element and Sum Less Than k

// SOLUTION :

class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
    
        long[][] prefixSum = new long[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long currentSum = grid[i][j];
                
                if (i > 0) currentSum += prefixSum[i - 1][j];
                if (j > 0) currentSum += prefixSum[i][j - 1];
                if (i > 0 && j > 0) currentSum -= prefixSum[i - 1][j - 1];
                
                prefixSum[i][j] = currentSum;
            
                if (currentSum <= k) {
                    count++;
                } else {
                    break;
                }
            }
        }
        
        return count;
    }
}
