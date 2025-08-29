// PROBLEM :- (3021) Alice and Bob Playing Flower Game

// SOLUTION :

class Solution {
public:
    long long flowerGame(int n, int m) {
        long long oddX = (n + 1) / 2;
        long long evenX = n / 2;
        long long oddY = (m + 1) / 2;
        long long evenY = m / 2;
        
        return oddX * evenY + evenX * oddY;
    }
};

        return (start == -1) ? "" : s.substr(start, minLen);
    }
};
