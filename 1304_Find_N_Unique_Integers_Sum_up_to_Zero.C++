// PROBLEM : (1304) Find N Unique Integers Sum up to Zero

// SOLUTION :

class Solution {
public:
    vector<int> sumZero(int n) {
        vector<int> ans;
        // add pairs (-i, i)
        for (int i = 1; i <= n / 2; i++) {
            ans.push_back(-i);
            ans.push_back(i);
        }
        // if n is odd, add 0
        if (n % 2 == 1) ans.push_back(0);
        return ans;
    }
};
