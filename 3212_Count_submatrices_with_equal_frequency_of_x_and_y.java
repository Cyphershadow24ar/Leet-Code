// PROBLEM : (3212) Count Submatrices With Equal Frequency of X and Y

// SOLUTION :

class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // 2D prefix sums for X and Y
        int[][] prefixX = new int[rows + 1][cols + 1];
        int[][] prefixY = new int[rows + 1][cols + 1];
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Calculate prefix sums for 'X'
                prefixX[i + 1][j + 1] = prefixX[i][j + 1] + prefixX[i + 1][j] - prefixX[i][j] 
                                        + (grid[i][j] == 'X' ? 1 : 0);
                
                // Calculate prefix sums for 'Y'
                prefixY[i + 1][j + 1] = prefixY[i][j + 1] + prefixY[i + 1][j] - prefixY[i][j] 
                                        + (grid[i][j] == 'Y' ? 1 : 0);

                // Conditions: Equal counts AND at least one 'X'
                if (prefixX[i + 1][j + 1] > 0 && prefixX[i + 1][j + 1] == prefixY[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        return result;
    }
}
