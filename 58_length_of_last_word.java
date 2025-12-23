// PROBLEM : (58) Length of Last Word

// SOLUTION : 

class Solution {
    public int lengthOfLastWord(String s) {
        // Trim trailing spaces first
        s = s.trim();

        // Find the last space in the trimmed string
        int lastSpaceIndex = s.lastIndexOf(' ');

        // If no space found, the entire string is the last word
        if (lastSpaceIndex == -1) {
            return s.length();
        }

        // Return length from last space to end of string
        return s.length() - lastSpaceIndex - 1;
    }
}
