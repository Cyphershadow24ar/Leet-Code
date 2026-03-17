// PROBLEM : (1727) Largest Submatrix With Rearrangements

// SOLUTION :
import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Step 1: Build heights
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        int maxArea = 0;
        // Step 2 & 3: Sort each row and calculate area
        for (int i = 0; i < m; i++) {
            int[] row = matrix[i].clone();
            Arrays.sort(row);

            // reverse traversal (descending)
            for (int j = n - 1; j >= 0; j--) {
                int height = row[j];
                int width = n - j; // number of columns used
                maxArea = Math.max(maxArea, height * width);
            }
        }
        return maxArea;
    }
}
