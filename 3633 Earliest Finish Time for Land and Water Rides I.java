// PROBLEM : (3633) Earliest Finish Time for Land and Water Rides I

// SOLUTION :

class Solution {

    public int earliestFinishTime(
        int[] landStartTime,
        int[] landDuration,
        int[] waterStartTime,
        int[] waterDuration
    ) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int land = landStartTime[i] + landDuration[i];
                int land_water =
                    Math.max(land, waterStartTime[j]) + waterDuration[j];
                res = Math.min(res, land_water);

                int water = waterStartTime[j] + waterDuration[j];
                int water_land =
                    Math.max(water, landStartTime[i]) + landDuration[i];
                res = Math.min(res, water_land);
            }
        }
        return res;
    }
}
