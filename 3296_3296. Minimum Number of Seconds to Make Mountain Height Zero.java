// PROBLEM :

// SOLUTION : (3296) 3296. Minimum Number of Seconds to Make Mountain Height Zero

class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long low = 0;
        // Upper bound: slowest case is one worker with max time reducing max height
        // Max time = 10^6 * (10^5 * 10^5 / 2) ≈ 5 * 10^15
        long high = 1_000_000L * (long)mountainHeight * (mountainHeight + 1) / 2;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canReduce(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean canReduce(long maxTime, int targetHeight, int[] workerTimes) {
        long totalHeightReduced = 0;
        for (int wTime : workerTimes) {
            // Solve: wTime * x * (x + 1) / 2 <= maxTime
            // x^2 + x - (2 * maxTime / wTime) <= 0
            double val = (2.0 * maxTime) / wTime;
            long x = (long)((-1 + Math.sqrt(1 + 4 * val)) / 2);
            
            totalHeightReduced += x;
            if (totalHeightReduced >= targetHeight) return true;
        }
        return totalHeightReduced >= targetHeight;
    }
}
