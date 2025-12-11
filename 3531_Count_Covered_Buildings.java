// PROBLEM  : (3531) Count Covered Buildings

// SOLUTION :

import java.util.Arrays;

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // We need arrays to store the bounds for rows and columns.
        // Size is n + 1 because coordinates are 1-based.
        int[] minRowInCol = new int[n + 1];
        int[] maxRowInCol = new int[n + 1];
        int[] minColInRow = new int[n + 1];
        int[] maxColInRow = new int[n + 1];

        // Initialize min arrays to Max Value and max arrays to Min Value
        Arrays.fill(minRowInCol, Integer.MAX_VALUE);
        Arrays.fill(maxRowInCol, Integer.MIN_VALUE);
        Arrays.fill(minColInRow, Integer.MAX_VALUE);
        Arrays.fill(maxColInRow, Integer.MIN_VALUE);

        // Pass 1: Populate the boundary arrays
        for (int[] b : buildings) {
            int r = b[0];
            int c = b[1];

            // Update bounds for the specific column 'c'
            if (r < minRowInCol[c]) minRowInCol[c] = r;
            if (r > maxRowInCol[c]) maxRowInCol[c] = r;

            // Update bounds for the specific row 'r'
            if (c < minColInRow[r]) minColInRow[r] = c;
            if (c > maxColInRow[r]) maxColInRow[r] = c;
        }

        int count = 0;

        // Pass 2: Check if each building is "covered"
        for (int[] b : buildings) {
            int r = b[0];
            int c = b[1];

            // Check Above: Is there a building in this col with a smaller row index?
            if (minRowInCol[c] < r && 
                // Check Below: Is there a building in this col with a larger row index?
                maxRowInCol[c] > r &&
                // Check Left: Is there a building in this row with a smaller col index?
                minColInRow[r] < c &&
                // Check Right: Is there a building in this row with a larger col index?
                maxColInRow[r] > c) {
                    
                count++;
            }
        }

        return count;
    }
}
