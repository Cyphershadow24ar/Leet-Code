// PROBLEM : (3464) Maximize the Distance Between Points on a Square

// SOLUTION :

import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] d = new long[n];
        
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) d[i] = x;
            else if (x == side) d[i] = side + y;
            else if (y == side) d[i] = 2L * side + (side - x);
            else d[i] = 3L * side + (side - y);
        }
        
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> Long.compare(d[a], d[b]));
        
        int[][] sortedPoints = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPoints[i] = points[indices[i]];
        }

        int low = 1, high = side, ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, sortedPoints, k, side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int minDist, int[][] pts, int k, int side) {
        int n = pts.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && getDist(pts[0], pts[i], side) > minDist) break;
            
            int count = 1;
            int lastIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (manhattan(pts[lastIdx], pts[j]) >= minDist) {
                    count++;
                    lastIdx = j;
                    if (count == k) break;
                }
            }

            if (count == k && manhattan(pts[lastIdx], pts[i]) >= minDist) {
                return true;
            }
        }
        return false;
    }

    private int manhattan(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
    
    private long getDist(int[] p1, int[] p2, int side) {
        return Math.abs((long)p1[0] - p2[0]) + Math.abs((long)p1[1] - p2[1]);
    }
}
