// PROBLEM : (1886) Determine Whether Matrix Can Be Obtained By Rotation

// SOLUTION :
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean[] rotate = {true, true, true, true}; // 0, 90, 180, 270

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) rotate[0] = false; // 0
                
                if (mat[i][j] != target[j][n - 1 - i]) rotate[1] = false; // 90
                
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) rotate[2] = false; // 180
                
                if (mat[i][j] != target[n - 1 - j][i]) rotate[3] = false; // 270
            }
        }

        return rotate[0] || rotate[1] || rotate[2] || rotate[3];
    }
}
