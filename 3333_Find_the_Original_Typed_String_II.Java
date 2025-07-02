// PROBLEM :- 3333_Find_the_Original_Typed_String_II

class Solution {
    static final long MOD = 1_000_000_000 + 7;

    public int possibleStringCount(String word, int k) {
        int len = word.length();
        if (len == k) return 1;

        List<Integer> block = new ArrayList<>();
        int i = 0;

        while (i < len) {
            int j = i + 1;
            while (j < len && word.charAt(j) == word.charAt(j - 1)) j++;
            block.add(j - i);
            i = j;
        }

        int cnt = block.size(); // Number of character blocks

        long[] mult = new long[cnt];
        mult[cnt - 1] = block.get(cnt - 1);
        for (i = cnt - 2; i >= 0; i--) {
            mult[i] = (mult[i + 1] * block.get(i)) % MOD;
        }

        if (cnt >= k) return (int) mult[0];

        long[][] dp = new long[cnt][k - cnt + 1]; // dp[i][extra_chars]

        // Base case: only the last block
        for (i = 0; i < k - cnt + 1; i++) {
            if (block.get(cnt - 1) + i + cnt > k) {
                dp[cnt - 1][i] = block.get(cnt - 1) - (k - cnt - i);
            }
        }

        // DP transitions from second-last to first block
        for (i = cnt - 2; i >= 0; i--) {
            long sum = (dp[i + 1][k - cnt] * block.get(i)) % MOD;
            for (int j = k - cnt; j >= 0; j--) {
                sum += dp[i + 1][j];
                if (j + block.get(i) > k - cnt) {
                    sum = (sum - dp[i + 1][k - cnt] + MOD) % MOD;
                } else {
                    sum = (sum - dp[i + 1][j + block.get(i)] + MOD) % MOD;
                }
                dp[i][j] = sum;
            }
        }

        return (int) dp[0][0];
    }
}
