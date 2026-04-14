// PROBLEM : (2463) Minimum Total Distance Traveled

// SOLUTION :

import java.util.*;

class Solution {
    long[][] dp;
    
    public long minimumTotalDistance(List<Integer> r, int[][] f) {
        int n = r.size();
        int m = f.length;

        Collections.sort(r);

        Arrays.sort(f, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        dp = new long[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = -1;
            }
        }
        
        return solve(0, 0, r, f);
    }
    
    long solve(int i, int j, List<Integer> r, int[][] f){
        int n = r.size();
        int m = f.length;

        if(i == n) return 0;

        if(j == m) return (long)1e15;
        
        if(dp[i][j] != -1) return dp[i][j];
        
        long ans = solve(i, j + 1, r, f);
        
        long cost = 0;
        int pos = f[j][0];
        int limit = f[j][1];
        
        for(int k = 0; k < limit && i + k < n; k++){
            cost += Math.abs(r.get(i + k) - pos);
            
            long next = solve(i + k + 1, j + 1, r, f);
            ans = Math.min(ans, cost + next);
        }
        
        return dp[i][j] = ans;
    }
}
