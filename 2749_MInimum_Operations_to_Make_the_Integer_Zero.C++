// PROBLEM : (2749) Minimum Operations to Make the Integer Zero

// SOLUTION :

class Solution {
public:
    int makeTheIntegerZero(int num1, int num2) {
        for (long long k = 1; k <= 60; k++) {
            long long target = (long long)num1 - k * (long long)num2;
            if (target < k) continue; // not enough to split into k parts
            if (__builtin_popcountll(target) <= k) return k;
        }
        return -1;
    }
};
