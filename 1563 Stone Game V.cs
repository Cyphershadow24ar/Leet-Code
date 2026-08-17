// PROBLEM : (1563) Stone Game V

// SOLUTION :

public class Solution {
    public int StoneGameV(int[] stoneValue) {
        int n = stoneValue.Length;
        int[][] f = new int [n][];
        int[][] maxl = new int [n][];
        int[][] maxr = new int [n][];

        for (int i = 0; i < n; i++) {
            f[i] = new int[n];
            maxl[i] = new int[n];
            maxr[i] = new int[n];
        }

        for (int left = n - 1; left >= 0; left--) {
            maxl[left][left] = maxr[left][left] = stoneValue[left];
            int total = stoneValue[left];
            int suml = 0;
            int i = left - 1;
            for (int right = left + 1; right < n; right++) {
                total += stoneValue[right];
                while (i + 1 < right &&
                       (suml + stoneValue[i + 1]) * 2 <= total) {
                    suml += stoneValue[i + 1];
                    i++;
                }
                if (left <= i) {
                    f[left][right] = Math.Max(f[left][right], maxl[left][i]);
                }
                if (i + 1 < right) {
                    f[left][right] =
                        Math.Max(f[left][right], maxr[i + 2][right]);
                }
                if (suml * 2 == total) {
                    f[left][right] =
                        Math.Max(f[left][right], maxr[i + 1][right]);
                }
                maxl[left][right] =
                    Math.Max(maxl[left][right - 1], total + f[left][right]);
                maxr[left][right] =
                    Math.Max(maxr[left + 1][right], total + f[left][right]);
            }
        }

        return f[0][n - 1];
    }
}
