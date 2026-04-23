// PROBLEM : (2615) Sum of Distances

// SOLUTION :

import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Group indices by their values
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Process each group of indices
        for (List<Integer> indices : map.values()) {
            int size = indices.size();
            if (size <= 1) continue;

            long totalSum = 0;
            for (int index : indices) {
                totalSum += index;
            }

            long prefixSum = 0;
            for (int i = 0; i < size; i++) {
                int curIndex = indices.get(i);
                
                // Sum of |curIndex - j| for all j in indices
                // Left Part: (count of elements on left * curIndex) - (sum of left indices)
                long leftCount = i;
                long leftSum = (leftCount * curIndex) - prefixSum;

                // Right Part: (sum of right indices) - (count of elements on right * curIndex)
                long rightCount = size - 1 - i;
                long rightSumAfter = totalSum - prefixSum - curIndex;
                long rightSum = rightSumAfter - (rightCount * curIndex);

                res[curIndex] = leftSum + rightSum;
                
                prefixSum += curIndex;
            }
        }

        return res;
    }
}
