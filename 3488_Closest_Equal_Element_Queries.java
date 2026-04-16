// PROBLEM : (3488) Closest Equal Element Queries

// SOLUTION :

import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        
        // Map each value to a list of all its indices in the array
        Map<Integer, List<Integer>> posMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            posMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Map to store the calculated result for each unique index
        // We use this to avoid redundant calculations if multiple queries point to same index
        Map<Integer, Integer> memo = new HashMap<>();
        List<Integer> result = new ArrayList<>(queries.length);

        for (int qIdx : queries) {
            // If we've already calculated the answer for this index, use it
            if (memo.containsKey(qIdx)) {
                result.add(memo.get(qIdx));
                continue;
            }

            int val = nums[qIdx];
            List<Integer> indices = posMap.get(val);

            if (indices.size() <= 1) {
                memo.put(qIdx, -1);
                result.add(-1);
            } else {
                // Find the position of qIdx in the sorted list of indices
                int posInList = Collections.binarySearch(indices, qIdx);
                int k = indices.size();

                // Get circular neighbors
                int prevIdx = indices.get((posInList - 1 + k) % k);
                int nextIdx = indices.get((posInList + 1) % k);

                // Calculate circular distances
                int d1 = Math.abs(qIdx - prevIdx);
                d1 = Math.min(d1, n - d1);

                int d2 = Math.abs(qIdx - nextIdx);
                d2 = Math.min(d2, n - d2);

                int minDist = Math.min(d1, d2);
                memo.put(qIdx, minDist);
                result.add(minDist);
            }
        }

        return result;
    }
}
