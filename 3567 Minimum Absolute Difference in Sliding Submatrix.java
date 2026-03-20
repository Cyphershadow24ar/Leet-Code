// PROBLEM : (3567) Minimum Absolute Difference in Sliding Submatrix

// SOLTUION :

import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int resM = m - k + 1;
        int resN = n - k + 1;
        int[][] ans = new int[resM][resN];

        for (int i = 0; i < resM; i++) {
            for (int j = 0; j < resN; j++) {
                ans[i][j] = getMinDiff(grid, i, j, k);
            }
        }

        return ans;
    }

    private int getMinDiff(int[][] grid, int r, int c, int k) {
        List<Integer> elements = new ArrayList<>();
        
        // Collect all elements in the k x k submatrix
        for (int i = r; i < r + k; i++) {
            for (int j = c; j < c + k; j++) {
                elements.add(grid[i][j]);
            }
        }

        // Sort to find the minimum difference between adjacent values
        Collections.sort(elements);

        int minDiff = Integer.MAX_VALUE;
        boolean foundDistinct = false;

        for (int i = 1; i < elements.size(); i++) {
            int diff = Math.abs(elements.get(i) - elements.get(i - 1));
            // We only care about distinct values. 
            // If diff is 0, it means the values are the same.
            if (diff > 0) {
                minDiff = Math.min(minDiff, diff);
                foundDistinct = true;
            }
        }

        // If no two distinct values were found, the problem implies 0
        return foundDistinct ? minDiff : 0;
    }
}
