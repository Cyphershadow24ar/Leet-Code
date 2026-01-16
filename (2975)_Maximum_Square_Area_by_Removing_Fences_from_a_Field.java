// PROBLEM : (2975) Maximum Square Area by Removing Fences from a Field 

// SOLUTION :

import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final int MOD = 1_000_000_007;

        // Add boundary fences
        int[] h = new int[hFences.length + 2];
        int[] v = new int[vFences.length + 2];

        h[0] = 1;
        h[h.length - 1] = m;
        for (int i = 0; i < hFences.length; i++) {
            h[i + 1] = hFences[i];
        }

        v[0] = 1;
        v[v.length - 1] = n;
        for (int i = 0; i < vFences.length; i++) {
            v[i + 1] = vFences[i];
        }

        Arrays.sort(h);
        Arrays.sort(v);

        // Store all possible heights
        Set<Long> heights = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                heights.add((long) (h[j] - h[i]));
            }
        }

        long maxSide = 0;

        // Check widths against heights
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                long width = v[j] - v[i];
                if (heights.contains(width)) {
                    maxSide = Math.max(maxSide, width);
                }
            }
        }

        if (maxSide == 0) return -1;

        return (int) ((maxSide * maxSide) % MOD);
    }
}
