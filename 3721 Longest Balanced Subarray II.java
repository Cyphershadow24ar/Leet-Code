// PROBLEM : (3721) Longest Balanced Subarray II

// SOLUTION :

import java.util.Arrays;

class Solution {
    // Segment Tree to store the 'balance' values for each starting position L
    int[] minVal, maxVal, lazy;

    void push(int v) {
        if (lazy[v] != 0) {
            minVal[2 * v] += lazy[v];
            maxVal[2 * v] += lazy[v];
            lazy[2 * v] += lazy[v];
            minVal[2 * v + 1] += lazy[v];
            maxVal[2 * v + 1] += lazy[v];
            lazy[2 * v + 1] += lazy[v];
            lazy[v] = 0;
        }
    }

    void update(int v, int tl, int tr, int l, int r, int add) {
        if (l > r) return;
        if (l == tl && r == tr) {
            minVal[v] += add;
            maxVal[v] += add;
            lazy[v] += add;
        } else {
            push(v);
            int tm = (tl + tr) / 2;
            update(2 * v, tl, tm, l, Math.min(r, tm), add);
            update(2 * v + 1, tm + 1, tr, Math.max(l, tm + 1), r, add);
            minVal[v] = Math.min(minVal[2 * v], minVal[2 * v + 1]);
            maxVal[v] = Math.max(maxVal[2 * v], maxVal[2 * v + 1]);
        }
    }

    // Find the leftmost index in [0, R] where the balance is exactly 0
    int findFirstZero(int v, int tl, int tr, int rLimit) {
        if (tl > rLimit || minVal[v] > 0 || maxVal[v] < 0) return -1;
        if (tl == tr) return tl;
        
        push(v);
        int tm = (tl + tr) / 2;
        int res = findFirstZero(2 * v, tl, tm, rLimit);
        if (res == -1) {
            res = findFirstZero(2 * v + 1, tm + 1, tr, rLimit);
        }
        return res;
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        minVal = new int[4 * n];
        maxVal = new int[4 * n];
        lazy = new int[4 * n];
        
        int[] lastPos = new int[100001];
        Arrays.fill(lastPos, -1);
        
        int maxLen = 0;
        for (int r = 0; r < n; r++) {
            int val = nums[r];
            int prev = lastPos[val];
            int delta = (val % 2 == 0) ? 1 : -1;
            
            // Update the balance for all subarrays starting after the last occurrence of this number
            update(1, 0, n - 1, prev + 1, r, delta);
            
            int firstL = findFirstZero(1, 0, n - 1, r);
            if (firstL != -1) {
                maxLen = Math.max(maxLen, r - firstL + 1);
            }
            
            lastPos[val] = r;
        }
        
        return maxLen;
    }
}
