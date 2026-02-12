// PROBLEM : (3713) Longest Balanced Substring I

// SOLUTION :

class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                freq[ch - 'a']++;

                int maxFreq = 0;
                int minFreq = Integer.MAX_VALUE;

                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) {
                        maxFreq = Math.max(maxFreq, freq[k]);
                        minFreq = Math.min(minFreq, freq[k]);  // ✅ fixed
                    }
                }

                if (maxFreq == minFreq) {
                    int length = j - i + 1;
                    maxLength = Math.max(maxLength, length);
                }
            }
        }
        return maxLength;
    }
}
