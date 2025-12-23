// PROBLEM : (459) Repeated Substring Pattern

// SOLUTION :

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;

        for (int i = 1; i < n;) {
            if (s.charAt(i) == s.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                i++;
            }
        }

        int longestPrefixSuffix = lps[n - 1];
        return longestPrefixSuffix > 0 && n % (n - longestPrefixSuffix) == 0;
    }
}

