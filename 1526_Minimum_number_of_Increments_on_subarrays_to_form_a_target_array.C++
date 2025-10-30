// PROBLEM : (1526) Minimum Number of Increments on Subarrays to Form a Target Array

// SOLUTION :

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minNumberOperations(vector<int>& target) {
        int n = target.size();
        int operations = target[0];
        
        for (int i = 1; i < n; ++i) {
            if (target[i] > target[i - 1])
                operations += target[i] - target[i - 1];
        }
        return operations;
    }
};
