// PROBLEM :- (342) Power of Four

// SOLUTION :- 

class Solution {
public:
    bool isPowerOfFour(int n) {
        if (n <= 0) return false; // must be positive
        if (n & (n - 1)) return false; // must be power of two (only one bit set)
        
        // Check if the single set bit is in an even position
        // 0x55555555 is binary: 01010101010101010101010101010101
        // which masks bits in even positions
        return (n & 0x55555555) != 0;
    }
};
