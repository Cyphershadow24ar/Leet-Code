
// PROBLEM:- (1751) Maximum Number of Events That Can Be Attended II

// SOLUTION :- 

class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events,(a,b) -> Integer.compare(a[1], b[1]));
        int n = events.length;

        int[] endtimes = new int[n];
        for(int i =0; i<n; i++){
            endtimes[i] = events[i][1];
        }

        int[][] dp = new int[n+1][k+1];

        for(int i =1; i<=n; i++){
            int[] curr = events[i-1];
            int start = curr[0];
            int end = curr[1];
            int value = curr[2];

            int idx = binarySearch(events, start, i-1);
            for(int j =1; j <= k; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[idx+1][j-1] + value);
            }
        }
        return dp[n][k];
    }

    private int binarySearch(int [][] events, int start, int right){
        int low = 0, high = right - 1;
        int res = -1;

        while(low <= high){
            int mid = (low + high) / 2;
            if(events[mid][1] < start){
                res = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return res;
    }
}
