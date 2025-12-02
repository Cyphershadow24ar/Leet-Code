// PROBLEM : (3623) Count Number of Trapezoids I

// SOLUTION :

class Solution {
    static final long MOD = 1_000_000_007;

    public int countTrapezoids(int[][] points) { 
        // Map: y-coordinate → count of points at that y
        HashMap<Integer, Long> map = new HashMap<>();

        for (int[] p : points) {
            map.put(p[1], map.getOrDefault(p[1], 0L) + 1);
        }

        long totalPairs = 0;     // Σ C(k,2)
        long sumSquares = 0;     // Σ (C(k,2)^2)

        for (long count : map.values()) {
            if (count >= 2) {
                long pairs = count * (count - 1) / 2;  // C(k, 2)
                pairs %= MOD;

                totalPairs = (totalPairs + pairs) % MOD;
                sumSquares = (sumSquares + (pairs * pairs) % MOD) % MOD;
            }
        }

        // Compute: (totalPairs^2 − sumSquares) / 2   modulo MOD
        long result = (totalPairs * totalPairs) % MOD;
        result = (result - sumSquares + MOD) % MOD;

        // multiply by modular inverse of 2
        long inv2 = (MOD + 1) / 2;  
        result = (result * inv2) % MOD;

        return (int) result;
    }
}
