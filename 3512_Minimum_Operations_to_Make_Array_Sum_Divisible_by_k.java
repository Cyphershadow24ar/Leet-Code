// PROBLEM : (3512) Minimum Operations to Make Array Sum Divisible by K

// SOLUTION :

class Solution {
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int r = sum % k;
        return r == 0 ? 0 : r;
    }
}
