// PROBLEM : (2033) Minimum Operations to Make a Uni-Value Grid

// SOLUTION :

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        
        int size = m * n;
        int[] a = new int[size];
        
        int k = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[k++] = grid[i][j];
            }
        }
        
        // Check feasibility
        int rem = a[0] % x;
        for (int i = 0; i < size; i++) {
            if (a[i] % x != rem) return -1;
        }
        
        // Sort
        java.util.Arrays.sort(a);
        
        // Median
        int med = a[size / 2];
        
        // Count operations
        int ops = 0;
        for (int i = 0; i < size; i++) {
            ops += Math.abs(a[i] - med) / x;
        }
        
        return ops;
    }
}
