// PROBLEM : (3013) Divide an Array Into Subarrays With Minimum Cost II

// SOLUTION :

class Solution {

    public long minimumCost(int[] nums, int k, int dist) {
        PriorityQueue<int[]> rest = new PriorityQueue<>(
            (v1, v2) -> Integer.compare(v1[1], v2[1])
        );
        PriorityQueue<int[]> kMin = new PriorityQueue<>(
            (v1, v2) -> {
                int cmp = Integer.compare(v2[1], v1[1]);
                // important for removing elements from the sliding window
                if (cmp == 0) return Integer.compare(v1[0], v2[0]);
                return cmp;
            }
        );
        long kSum = 0;
        for (int i = 1; i < k; i++) {
            kMin.add(new int[] { i, nums[i] });
            kSum += nums[i];
        }
        int end = k;
        long sum = kSum;
        while (end < nums.length) {
            // start of the sliding window
            int minAllowed = end - dist;
            pop(rest, minAllowed);
            rest.add(new int[] { end, nums[end] });
            // sliding window longer than dist
            if (minAllowed > 1) {
                if (nums[minAllowed - 1] < kMin.peek()[1] || minAllowed - 1 == kMin.peek()[0]) {
                    int[] moved = rest.poll();
                    kMin.add(moved);
                    kSum += moved[1] - nums[minAllowed - 1];
                }
            }
            pop(rest, minAllowed);
            pop(kMin, minAllowed);
            // swap elements if nums[end] we included is smaller than one of kMin
            if (rest.size() > 0 && rest.peek()[1] < kMin.peek()[1]) {
                int[] rem = kMin.poll();
                int[] add = rest.poll();
                rest.add(rem);
                kMin.add(add);
                kSum += add[1] - rem[1];
            }
            sum = Math.min(sum, kSum);
            end++;
        }
        return sum + nums[0];
    }

    private void pop(PriorityQueue<int[]> queue, int start) {
        while (queue.size() > 0 && queue.peek()[0] < start) queue.poll();
    }
}
