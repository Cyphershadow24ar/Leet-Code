// PROBLEM : (3495) Minimum Operations to Make Array Elements Zero

// SOLUTION :

class Solution {
public:
    // compute steps for a single number
    int steps(long long x) {
        int cnt = 0;
        while (x > 0) {
            x /= 4;
            cnt++;
        }
        return cnt;
    }
    
    // prefix sum of steps(1..n)
    long long prefix(long long n) {
        if (n <= 0) return 0;
        long long res = 0;
        long long start = 1;
        int level = 1;
        
        while (start <= n) {
            long long end = min(n, start * 4 - 1);
            long long cnt = end - start + 1;
            res += cnt * level;
            start *= 4;
            level++;
        }
        return res;
    }

    long long minOperations(vector<vector<int>>& queries) {
        long long total = 0;
        for (auto &q : queries) {
            long long l = q[0], r = q[1];
            long long sumSteps = prefix(r) - prefix(l - 1);
            long long ops = (sumSteps + 1) / 2; // ceil
            total += ops;
        }
        return total;
    }
};
