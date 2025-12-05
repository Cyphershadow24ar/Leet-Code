// PROBLEM : (3432) Count Partitions with Even Sum Difference

// SOLUTION :

class Solution {
    public int countPartitions(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // If total sum is even, ALL valid partitions (n-1) work.
        // If total sum is odd, NONE work.
        return (totalSum % 2 == 0) ? nums.length - 1 : 0;
    }
}
