// PROBLEM : (3661) Maximum Walls Destroyed by Robots

// SOLUTION :

import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] rData = new int[n][2];
        for (int i = 0; i < n; i++) {
            rData[i][0] = robots[i];
            rData[i][1] = distance[i];
        }
        Arrays.sort(rData, (a, b) -> a[0] - b[0]);
        Arrays.sort(walls);

        long[][] dp = new long[n][2];

        dp[0][0] = count(walls, rData[0][0] - rData[0][1], rData[0][0]);
        dp[0][1] = count(walls, rData[0][0], Math.min(rData[0][0] + rData[0][1], 
                         n > 1 ? rData[1][0] - 1 : Integer.MAX_VALUE));

        for (int i = 1; i < n; i++) {
            int currPos = rData[i][0];
            int currDist = rData[i][1];
            int prevPos = rData[i - 1][0];
            
            long leftIfPrevLeft = dp[i - 1][0] + count(walls, Math.max(currPos - currDist, prevPos + 1), currPos);
            
            int prevRightReach = Math.min(prevPos + rData[i - 1][1], currPos - 1);
            int currLeftReach = Math.max(currPos - currDist, prevPos + 1);
            long combinedGapWalls = countUnion(walls, prevPos, prevRightReach, currLeftReach, currPos);
            long leftIfPrevRight = (i > 1 ? Math.max(dp[i - 2][0], dp[i - 2][1]) : 0) + combinedGapWalls;
            
            dp[i][0] = Math.max(leftIfPrevLeft, leftIfPrevRight);

            int rightLimit = (i == n - 1) ? Integer.MAX_VALUE : rData[i + 1][0] - 1;
            int rightReach = Math.min(currPos + currDist, rightLimit);
            long currentRightCount = count(walls, currPos, rightReach);
            
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + currentRightCount;
        }

        return (int) Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    private int count(int[] walls, int low, int high) {
        if (low > high) return 0;
        int start = Arrays.binarySearch(walls, low);
        if (start < 0) start = -(start + 1);
        int end = Arrays.binarySearch(walls, high);
        if (end < 0) end = -(end + 1) - 1;
        return (start > end) ? 0 : end - start + 1;
    }

    private int countUnion(int[] walls, int p1, int r1, int l2, int p2) {
        if (r1 >= l2) {
            return count(walls, p1, p2);
        } else {
            return count(walls, p1, r1) + count(walls, l2, p2);
        }
    }
}
