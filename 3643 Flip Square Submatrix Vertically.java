// PROBLEM : (3643) Flip Square Submatrix Vertically

// SOLUTION :

class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for(int i=0; i<k/2; i++){
            int r1 = x + i;
            int r2 = x + k - 1 - i;

            for(int j=0; j<k; j++){
                int col = y+j;

                int temp = grid[r1][col];
                grid[r1][col] = grid[r2][col];
                grid[r2][col] = temp;
            }
        }
        return grid;
    }
}
