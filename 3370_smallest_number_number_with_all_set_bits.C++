// PROBLEM : (3370) Smallest Number With All Set Bits

// Solution :

class Solution {
public:
    int smallestNumber(int n) {
        int x = 1;
        while ((1 << x) - 1 < n) {
            x++;
        }
        return (1 << x) - 1;
    }
};
