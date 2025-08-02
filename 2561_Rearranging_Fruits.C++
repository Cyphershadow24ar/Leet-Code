// PROBLEM :- (2561) Rearranging Fruits 

// SOLUTION :-

class Solution {
public:
    long long minCost(vector<int>& basket1, vector<int>& basket2) {
        map<int, int> freq;

        // Count total frequency
        for (int val : basket1) freq[val]++;
        for (int val : basket2) freq[val]--;

        vector<int> excess;

        // Check feasibility
        for (auto& [val, count] : freq) {
            if (count % 2 != 0) return -1; // Not even -> impossible

            // Add |count| / 2 times to excess list
            for (int i = 0; i < abs(count) / 2; ++i)
                excess.push_back(val);
        }

        // Nothing to swap
        if (excess.empty()) return 0;

        sort(excess.begin(), excess.end());

        // Find global minimum fruit cost
        int minElement = min(*min_element(basket1.begin(), basket1.end()),
                             *min_element(basket2.begin(), basket2.end()));

        long long cost = 0;
        int n = excess.size() / 2;
        for (int i = 0; i < n; ++i) {
            cost += min((long long)excess[i], (long long)2 * minElement);
        }

        return cost;
    }
};

