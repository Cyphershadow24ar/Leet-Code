// PROBLEM : (2110) Number of Smooth Descent Periods of a Stock

// SOLUTION :

class Solution {
    public long getDescentPeriods(int[] prices) {
        // We use long because the total number of periods can exceed Integer.MAX_VALUE 
        // if the array is large (e.g., 10^5 decreasing elements).
        long totalPeriods = 1; 
        long currentLength = 1;

        // Start loop from the second element
        for (int i = 1; i < prices.length; i++) {
            // Check if current price is exactly 1 less than the previous day
            if (prices[i] == prices[i - 1] - 1) {
                currentLength++;
            } else {
                // Sequence broken, reset length to 1 (for the current element itself)
                currentLength = 1;
            }
            // Add the number of smooth descent periods ending at this index
            totalPeriods += currentLength;
        }

        return totalPeriods;
    }
}
