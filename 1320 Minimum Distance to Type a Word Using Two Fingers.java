// PROBLEM : (1320) Minimum Distance to Type a Word Using Two Fingers

// SOLUTION :

class Solution {
    private int[][] memo;
    private int[] chars;

    public int minimumDistance(String word) {
        int n = word.length();
        chars = new int[n];
        
        // Convert characters to 0-indexed integers (A=0, B=1... Z=25)
        for (int i = 0; i < n; i++) {
            chars[i] = word.charAt(i) - 'A';
        }
        
        // memo[index][other_finger_position]
        // 27 states for the fingers (0-25 for letters, 26 for "unplaced")
        memo = new int[n][27];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }
        
        // Start typing from index 0. The "other" finger is currently unplaced (26).
        return dfs(0, 26);
    }

    private int dfs(int i, int other) {
        // Base case: We've typed the whole word
        if (i == chars.length) {
            return 0;
        }
        
        // Return cached result if we've seen this state before
        if (memo[i][other] != -1) {
            return memo[i][other];
        }

        // The finger that typed the previous character is currently at chars[i-1].
        // If i == 0, we treat it as "unplaced" (state 26).
        int prev = (i == 0) ? 26 : chars[i - 1];

        // Option 1: Move the finger that typed the PREVIOUS character to the CURRENT character
        int cost1 = getDistance(prev, chars[i]) + dfs(i + 1, other);

        // Option 2: Move the OTHER finger to the CURRENT character
        int cost2 = getDistance(other, chars[i]) + dfs(i + 1, prev);

        // Cache and return the minimum of the two choices
        memo[i][other] = Math.min(cost1, cost2);
        return memo[i][other];
    }

    private int getDistance(int a, int b) {
        // If either finger hasn't been placed yet, moving it costs 0.
        if (a == 26 || b == 26) {
            return 0; 
        }
        
        // Calculate 2D coordinates on a 6-width keyboard
        int rowA = a / 6, colA = a % 6;
        int rowB = b / 6, colB = b % 6;
        
        // Manhattan distance: |x1 - x2| + |y1 - y2|
        return Math.abs(rowA - rowB) + Math.abs(colA - colB);
    }
}
