// PROBLEM : (2906) Construct Product Matrix

// SOLUTION :

class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];
        int mod = 12345;

        long runpro = 1;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                p[i][j] = (int) (runpro % mod);
                runpro = (runpro * (grid[i][j] % mod)) % mod;
            }
        }

        runpro = 1;
        for(int i=n-1; i>=0; i--){
            for(int j= m-1; j >= 0; j--){
                p[i][j] = (int) ((p[i][j] * runpro) % mod);
                runpro = (runpro * (grid[i][j] % mod)) % mod;
            }
        }
        return p;
    }
}
