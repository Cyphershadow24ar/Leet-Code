//  PROBLEM : (762) Prime Number of Set Bits in Binary Representation

// SOLUTION :

class Solution {
    public int countPrimeSetBits(int left, int right) {
        int totalCount = 0;
        
        for (int i = left; i <= right; i++) {
            int setBits = Integer.bitCount(i);
            if (isPrime(setBits)) {
                totalCount++;
            }
        }
        
        return totalCount;
    }

    private boolean isPrime(int n) {
        // Since right <= 10^6, the max bits is 20.
        // Primes < 20: 2, 3, 5, 7, 11, 13, 17, 19
        return (n == 2 || n == 3 || n == 5 || n == 7 || 
                n == 11 || n == 13 || n == 17 || n == 19);
    }
}
