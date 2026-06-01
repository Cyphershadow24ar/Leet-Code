// PROBLEM : (2144) Minimum Cost of Buying Candies With Discount

// SOLUTION :

class Solution {
    public int minimumCost(int[] cost) {
        int n  = cost.length;
        Arrays.sort(cost);
        int res = 0;
        for(int i = n-1; i>= 0; --i){
            if((n-1-i) % 3 != 2){
                res += cost[i];
            }
        }
        return res;
    }
}
