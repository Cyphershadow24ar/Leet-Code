// PROBLEM : (1680) Concatenation of Consecutive Binary Numbers

// SOLUTION :

class Solution {
    static final long mod = 1000000007L;

    public int concatenatedBinary(int n) {
        long res = 0;
        int bitlen = 0;

        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                bitlen++;
            }
            res = ((res << bitlen) | i) % mod;
        }
        return (int) res;
    }
}
