// PORBLEM - (2163) Minimum Difference in Sums After Removal of Elements

// SOLUTOIn :- 

class Solution {
public:
    long long minimumDifference(vector<int>& nums) {
        int n = nums.size() / 3;
        int size = nums.size();
        
        // prefix: smallest n elements from left
        vector<long long> left_sum(size, 0);
        priority_queue<int> max_heap; // store largest among chosen, so we can pop it if needed
        long long sum = 0;
        for (int i = 0; i < 2 * n; i++) {
            sum += nums[i];
            max_heap.push(nums[i]);
            if ((int)max_heap.size() > n) {
                sum -= max_heap.top();
                max_heap.pop();
            }
            if ((int)max_heap.size() == n) {
                left_sum[i] = sum;
            } else {
                left_sum[i] = LLONG_MAX; // not enough elements yet
            }
        }

        // suffix: largest n elements from right
        vector<long long> right_sum(size, 0);
        priority_queue<int, vector<int>, greater<int>> min_heap; // store smallest among chosen, so we can pop it if needed
        sum = 0;
        for (int i = size - 1; i >= n; i--) {
            sum += nums[i];
            min_heap.push(nums[i]);
            if ((int)min_heap.size() > n) {
                sum -= min_heap.top();
                min_heap.pop();
            }
            if ((int)min_heap.size() == n) {
                right_sum[i] = sum;
            } else {
                right_sum[i] = LLONG_MIN; // not enough elements yet
            }
        }

        // calculate min difference
        long long ans = LLONG_MAX;
        for (int i = n - 1; i < 2 * n; i++) {
            if (left_sum[i] != LLONG_MAX && right_sum[i + 1] != LLONG_MIN) {
                ans = min(ans, left_sum[i] - right_sum[i + 1]);
            }
        }

        return ans;
    }
};
