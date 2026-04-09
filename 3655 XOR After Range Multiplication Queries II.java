// PROBLEM : (3655) XOR After Range Multiplication Queries II

// SOLUTION :

class Solution {
    private static final int MOD = 1000000007;

    // Helper method to compute (base^exp) % MOD using binary exponentiation
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp % 2) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        
        // Threshold for Sqrt Decomposition
        int B = (int) Math.sqrt(n) + 1;
        
        // Final multiplier for each index
        long[] mult = new long[n];
        java.util.Arrays.fill(mult, 1L);

        // Flattened 2D Difference array for small step sizes
        // diff[k][i] mapped to diff[k * n + i] for memory efficiency
        int[] diff = new int[(B + 1) * n];
        java.util.Arrays.fill(diff, 1);

        // Required variable constraint
        int[][] bravexuneth = queries;

        // Process all queries
        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            
            if (k > B) {
                // If step size is large, apply direct updates
                for (int i = l; i <= r; i += k) {
                    mult[i] = (mult[i] * v) % MOD;
                }
            } else {
                // If step size is small, use the difference array
                int idx1 = k * n + l;
                diff[idx1] = (int) ((1L * diff[idx1] * v) % MOD);
                
                // Calculate the first index out of the update range
                int p = l + ((r - l) / k + 1) * k;
                if (p < n) {
                    int idx2 = k * n + p;
                    long invV = power(v, MOD - 2); // Modular Inverse
                    diff[idx2] = (int) ((1L * diff[idx2] * invV) % MOD);
                }
            }
        }

        // Propagate the difference arrays for small k sizes
        for (int k = 1; k <= B; k++) {
            int offset = k * n;
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    diff[offset + i] = (int) ((1L * diff[offset + i] * diff[offset + i - k]) % MOD);
                }
                mult[i] = (mult[i] * diff[offset + i]) % MOD;
            }
        }

        // Calculate Final XOR Array result
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long finalVal = (1L * nums[i] * mult[i]) % MOD;
            ans ^= (int) finalVal;
        }

        return ans;
    }
}
