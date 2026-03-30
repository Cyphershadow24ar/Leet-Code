// PROBLEM : (2840) Check if Strings Can be Made Equal With Operations II

// SOLUTION :

class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        // Frequency arrays for even and odd positions
        int[] evenFreq1 = new int[26];
        int[] oddFreq1 = new int[26];
        int[] evenFreq2 = new int[26];
        int[] oddFreq2 = new int[26];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evenFreq1[s1.charAt(i) - 'a']++;
                evenFreq2[s2.charAt(i) - 'a']++;
            } else {
                oddFreq1[s1.charAt(i) - 'a']++;
                oddFreq2[s2.charAt(i) - 'a']++;
            }
        }

        // Compare frequencies
        for (int i = 0; i < 26; i++) {
            if (evenFreq1[i] != evenFreq2[i] || oddFreq1[i] != oddFreq2[i]) {
                return false;
            }
        }

        return true;
    }
}
