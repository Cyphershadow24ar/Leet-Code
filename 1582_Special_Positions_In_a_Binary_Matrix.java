// PROBLEM : (1582) Special Positions in a Binary Matrix

// SOLUTION :

class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowCounts = new int[m];
        int[] colCounts = new int[n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowCounts[i]++;
                    colCounts[j]++;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && rowCounts[i] == 1 && colCounts[j] == 1) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}
