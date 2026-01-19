// PROBLEM : (1292) Maximum Side Length of a Square with Sum less than or Equal to threshold

// SOLUTION :

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;

        // Prefix sum array
        int[][] pref = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pref[i][j] = mat[i - 1][j - 1]
                          + pref[i - 1][j]
                          + pref[i][j - 1]
                          - pref[i - 1][j - 1];
            }
        }

        int low = 0, high = Math.min(m, n), ans = 0;

        // Binary search on side length
        while (low <= high) {
            int mid = (low + high) / 2;
            if (existsSquare(pref, m, n, mid, threshold)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Check if there exists any square of side `len` with sum <= threshold
    private boolean existsSquare(int[][] pref, int m, int n, int len, int threshold) {
        if (len == 0) return true;
        for (int i = len; i <= m; i++) {
            for (int j = len; j <= n; j++) {
                int sum = pref[i][j]
                        - pref[i - len][j]
                        - pref[i][j - len]
                        + pref[i - len][j - len];
                if (sum <= threshold) return true;
            }
        }
        return false;
    }
}


