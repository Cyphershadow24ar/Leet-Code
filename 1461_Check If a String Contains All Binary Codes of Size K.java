// PROBLEM : (1461) Check If a String Contains All Binary Codes of Size K

// SOLUTION :

class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        
        // If total possible substrings < 2^k, impossible
        if (n < k) return false;
        
        int total = 1 << k;  // 2^k
        boolean[] seen = new boolean[total];
        int count = 0;
        
        int hash = 0;
        int mask = total - 1;  // To keep only k bits
        
        for (int i = 0; i < n; i++) {
            // Shift left and add current bit
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            
            // Start checking once window size reaches k
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;
                    
                    if (count == total) return true;
                }
            }
        }
        
        return false;
    }
}
