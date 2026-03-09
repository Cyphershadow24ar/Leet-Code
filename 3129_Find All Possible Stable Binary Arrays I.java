// PROBLEM : (3129) Find All Possible Stable Binary Arrays I

// SOLUTION :

class Solution {

    static final int MOD = 1000000007;

    public int numberOfStableArrays(int zero, int one, int limit) {

        long[][] dp0 = new long[zero + 1][one + 1];
        long[][] dp1 = new long[zero + 1][one + 1];

        for (int i = 1; i <= zero; i++) {
            dp0[i][0] = (i <= limit) ? 1 : 0;
        }

        for (int j = 1; j <= one; j++) {
            dp1[0][j] = (j <= limit) ? 1 : 0;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {

                dp0[i][j] = (dp0[i-1][j] + dp1[i-1][j]) % MOD;

                if (i - limit - 1 >= 0) {
                    dp0[i][j] = (dp0[i][j] - dp1[i-limit-1][j] + MOD) % MOD;
                }

                dp1[i][j] = (dp0[i][j-1] + dp1[i][j-1]) % MOD;

                if (j - limit - 1 >= 0) {
                    dp1[i][j] = (dp1[i][j] - dp0[i][j-limit-1] + MOD) % MOD;
                }
            }
        }

        return (int)((dp0[zero][one] + dp1[zero][one]) % MOD);
    }
}
