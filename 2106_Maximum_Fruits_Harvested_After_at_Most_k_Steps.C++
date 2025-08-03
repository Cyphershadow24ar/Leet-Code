/* PROBLEM :- (2106) Maximum Fruits Harvested After at Most K Steps

// SOLUTION :-

class Solution {
public:
    int maxTotalFruits(vector<vector<int>>& fruits, int startPos, int k) {
        int n = fruits.size();
        int maxFruits = 0;
        int windowSum = 0;

        int left = 0;

        for (int right = 0; right < n; ++right) {
            windowSum += fruits[right][1];

            while (left <= right) {
                int l = fruits[left][0];
                int r = fruits[right][0];

                // Calculate steps needed to visit [l, r]
                int steps = min(abs(startPos - l) + (r - l),
                                abs(startPos - r) + (r - l));

                if (steps <= k) break;

                windowSum -= fruits[left][1];
                left++;
            }

            maxFruits = max(maxFruits, windowSum);
        }

        return maxFruits;
    }
};
