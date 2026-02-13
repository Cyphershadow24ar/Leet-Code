// PROBLEM : (3714) Longest Balanced Substring II

// SOLUTION :

import java.util.*;

class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        int maxLen = 0;

        // Case 1: Substrings with only ONE distinct character
        // Example: "aaaa" -> length 4
        int currentStreak = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentStreak++;
            } else {
                maxLen = Math.max(maxLen, currentStreak);
                currentStreak = 1;
            }
        }
        maxLen = Math.max(maxLen, currentStreak);

        // Case 2: Substrings with TWO distinct characters (e.g., 'a' and 'b')
        // We must ensure the 3rd character is NOT present in the substring.
        maxLen = Math.max(maxLen, solveForSet(s, new char[]{'a', 'b'}));
        maxLen = Math.max(maxLen, solveForSet(s, new char[]{'b', 'c'}));
        maxLen = Math.max(maxLen, solveForSet(s, new char[]{'a', 'c'}));

        // Case 3: Substrings with THREE distinct characters ('a', 'b', and 'c')
        maxLen = Math.max(maxLen, solveForSet(s, new char[]{'a', 'b', 'c'}));

        return maxLen;
    }

    private int solveForSet(String s, char[] set) {
        Map<String, Integer> map = new HashMap<>();
        int[] counts = new int[3];
        
        // Initial state
        map.put(getDiffKey(set, counts), -1);
        
        int localMax = 0;
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            
            // For the substring to contain ONLY the characters in 'set',
            // the counts of excluded characters must remain unchanged.
            // This is handled by including the counts of excluded chars in the key.
            String key = getDiffKey(set, counts);
            
            if (map.containsKey(key)) {
                int prevIdx = map.get(key);
                // Validation: A balanced substring of multiple distinct chars 
                // MUST actually contain all characters in the set at least once.
                // However, the logic below handles the "same count" property.
                localMax = Math.max(localMax, i - prevIdx);
            } else {
                map.put(key, i);
            }
        }
        return localMax;
    }

    private String getDiffKey(char[] set, int[] counts) {
        StringBuilder sb = new StringBuilder();
        
        // 1. Maintain the relative difference between characters in the set
        for (int i = 0; i < set.length - 1; i++) {
            sb.append(counts[set[i] - 'a'] - counts[set[i+1] - 'a']).append(",");
        }
        
        // 2. Lock the counts of characters NOT in the set
        // This ensures the substring doesn't contain a character that shouldn't be there.
        boolean[] inSet = new boolean[3];
        for (char c : set) inSet[c - 'a'] = true;
        
        for (int i = 0; i < 3; i++) {
            if (!inSet[i]) {
                sb.append("|").append(counts[i]);
            }
        }
        
        return sb.toString();
    }
}
