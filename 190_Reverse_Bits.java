// PROBLEM : (190) Reverse Bits

// SOLUTION :

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // 1. Shift result to the left to make space for the new bit
            result <<= 1;
            
            // 2. Get the last bit of n and add it to result
            // (n & 1) extracts the LSB (Least Significant Bit)
            result |= (n & 1);
            
            // 3. Shift n to the right to look at the next bit
            n >>= 1;
        }
        return result;
    }
}
