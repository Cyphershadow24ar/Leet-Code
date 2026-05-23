// PROBLEM : (1752) Check if Array Is Sorted and Rotated

// SOLUTION :

class Solution {
    public boolean check(int[] nums) {
        boolean fault = false;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                if (fault) return false;
                fault = true;
            }
        }

        return true;
    }
}
