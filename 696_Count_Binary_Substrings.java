// PROBLEM : (696) Count Binary Substrings

// SOLUTION :

class Solution {
    public int countBinarySubstrings(String s) {
        int count = 0;
        int prevGroupLen = 0;
        int currGroupLen = 1;

        for (int i = 1; i < s.length(); i++) {
            // If the character stays the same, increment current group
            if (s.charAt(i) == s.charAt(i - 1)) {
                currGroupLen++;
            } else {
                // If character changes, we finished a group.
                // The number of valid substrings between prev and curr is min(prev, curr).
                count += Math.min(prevGroupLen, currGroupLen);
                
                // Move current to previous and reset current
                prevGroupLen = currGroupLen;
                currGroupLen = 1;
            }
        }
        
        // Don't forget the last comparison after the loop ends!
        return count + Math.min(prevGroupLen, currGroupLen);
    }
}
