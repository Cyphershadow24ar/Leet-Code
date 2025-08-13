// PROBLEM :- (326) Power of Three

// SOLUTION :-
class Solution {
public:
    bool isPowerOfThree(int n) {
        // 3^19 = 1162261467 is the largest power of three that fits in a 32-bit signed int
        return n > 0 && 1162261467 % n == 0;
    }
};
