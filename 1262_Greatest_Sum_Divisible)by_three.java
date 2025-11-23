// PROBLEM : (1262) Greatest Sum Divisible by Three

// SOLUTION :

class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp ={0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(int num : nums){
            int[] nextDp = java.util.Arrays.copyOf(dp, 3);
            for(int i = 0; i<3; i++){
                if(dp[i] != Integer.MIN_VALUE){
                    int newSum = dp[i] + num;
                    int remainder = newSum % 3;
                    nextDp[remainder] = Math.max(nextDp[remainder], newSum);
                }
            }
            dp = nextDp;
        }
        return dp[0];
    }
}
