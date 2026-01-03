// PROBLEM : (1411) Number of Ways to Paint N × 3 Grid

// SOLUTION :

class Solution {
    public int numOfWays(int n) {
        long MOD = 1_000_000_007;
        
        // Base case for n = 1
        long same = 6;  // ABA patterns
        long diff = 6;  // ABC patterns
        
        for (int i = 2; i <= n; i++) {
            long newSame = (same * 3 + diff * 2) % MOD;
            long newDiff = (same * 2 + diff * 2) % MOD;
            
            same = newSame;
            diff = newDiff;
        }
        
        return (int)((same + diff) % MOD);
    }
}
