// PROBLEM : (3318) Find X-Sum of All K-Long Subarrays I

// SOLUTION :

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> findXSum(vector<int>& nums, int k, int x) {
        int n = nums.size();
        vector<int> ans;

        for (int i = 0; i + k <= n; i++) {
            unordered_map<int, int> freq;

            // Count frequencies in window
            for (int j = i; j < i + k; j++) {
                freq[nums[j]]++;
            }

            // Sort by (frequency desc, value desc)
            vector<pair<int,int>> vec(freq.begin(), freq.end());
            sort(vec.begin(), vec.end(), [](auto &a, auto &b){
                if (a.second == b.second)
                    return a.first > b.first;
                return a.second > b.second;
            });

            // Keep only top x elements
            unordered_set<int> keep;
            for (int t = 0; t < x && t < vec.size(); t++)
                keep.insert(vec[t].first);

            // Sum of only kept elements
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                if (keep.count(nums[j]))
                    sum += nums[j];
            }

            ans.push_back(sum);
        }

        return ans;
    }
};
