// PROBLEM : (3578) Count Partitions With Max-Min Difference at Most K

// SOLUTION :

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int countPartitions(int[] nums, int k) {
        final int M = 1_000_000_007;
        int n = nums.length;

        // dp[i]: number of ways to partition nums[0...i-1] (first i elements)
        // dp size is n + 1 (for indices 0 to n)
        long[] dp = new long[n + 1];
        
        // P[i]: prefix sum of dp array, P[i] = dp[0] + ... + dp[i-1]
        // P size is n + 1 (for indices 0 to n, P[n] is sum up to dp[n-1])
        long[] P = new long[n + 1]; 

        // Base case: dp[0] = 1 (one way to partition an empty prefix)
        dp[0] = 1;
        P[0] = 0; // sum of nothing
        P[1] = 1; // P[1] = dp[0]

        // L is the left pointer (smallest valid starting index for a segment ending at i-1)
        int L = 0;
        
        // Monotonic Deques to find max and min in the window nums[L...i-1] in O(1)
        // Stores indices
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        // i iterates from 1 to n (calculating dp[i] for prefix of length i)
        for (int i = 1; i <= n; i++) {
            int currentVal = nums[i - 1]; // The element at the end of the prefix/segment
            
            // 1. Update Deques (Add nums[i-1])
            // Max Deque: stores indices in descending order of value (largest is at front)
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= currentVal) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(i - 1);

            // Min Deque: stores indices in ascending order of value (smallest is at front)
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= currentVal) {
                minDeque.pollLast();
            }
            minDeque.addLast(i - 1);

            // 2. Shrink Window (Update L)
            // L is the smallest starting index j such that nums[j...i-1] is valid.
            while (!maxDeque.isEmpty() && !minDeque.isEmpty()) {
                long max = nums[maxDeque.peekFirst()];
                long min = nums[minDeque.peekFirst()];

                if (max - min > k) {
                    // Window is invalid, must shrink from the left (increment L)
                    
                    // The element at L is now out of the window [L+1...i-1]
                    if (maxDeque.peekFirst() == L) {
                        maxDeque.pollFirst();
                    }
                    if (minDeque.peekFirst() == L) {
                        minDeque.pollFirst();
                    }
                    L++;
                } else {
                    // Window nums[L...i-1] is valid, so stop shrinking.
                    // Any segment starting at j >= L and ending at i-1 is valid.
                    break;
                }
            }
            
            // 3. Calculate dp[i]
            // dp[i] = sum(dp[j]) for j from L to i-1
            // This is P[i] - P[L]
            
            // P[i] is sum(dp[0]...dp[i-1])
            // P[L] is sum(dp[0]...dp[L-1])
            // P[i] - P[L] = sum(dp[L]...dp[i-1])
            
            long sum = (P[i] - P[L] + M) % M;
            dp[i] = sum;

            // 4. Update P[i+1]
            P[i + 1] = (P[i] + dp[i]) % M;
        }

        return (int) dp[n];
    }
}
