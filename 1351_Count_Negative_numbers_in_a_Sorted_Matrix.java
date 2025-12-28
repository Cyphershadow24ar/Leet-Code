// PROBELM : (1351) Count Negative Numbers in a Sorted Matrix

// SOLUITON :

class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int row = 0;
        int col = n - 1;
        int count = 0;

        while (row < m && col >= 0) {
            if (grid[row][col] < 0) {
                count += (m - row);
                col--; // move left
            } else {
                row++; // move down
            }
        }
        return count;
    }
}
