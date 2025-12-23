// PROBLEM : (2054) Two Best Non-Overlapping Events

// SOLUTION : 

import java.util.*;

class Solution {
    public int maxTwoEvents(int[][] events) {
        // Sort events by start time
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        // Min-heap sorted by end time
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] - b[1]
        );

        int maxValueSoFar = 0;
        int ans = 0;

        for (int[] event : events) {
            int start = event[0];
            int end = event[1];
            int value = event[2];

            // Remove non-overlapping past events
            while (!pq.isEmpty() && pq.peek()[1] < start) {
                maxValueSoFar = Math.max(maxValueSoFar, pq.poll()[2]);
            }

            // Take current event alone OR combine with best previous
            ans = Math.max(ans, value + maxValueSoFar);

            // Add current event to heap
            pq.offer(event);
        }

        return ans;
    }
}
