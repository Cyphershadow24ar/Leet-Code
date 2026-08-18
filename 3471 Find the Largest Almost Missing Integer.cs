// PROBLEM : (3471) Find the Largest Almost Missing Integer

// SOLUTION :

public class Solution {
    public int LargestInteger(int[] nums, int k) {
        int n = nums.Length;
        if (n == k) {
            return nums.Max();
        }
        int[] count = new int[51];
        foreach (int x in nums) {
            count[x]++;
        }
        if (k == 1) {
            for (int i = 50; i >= 0; --i) {
                if (count[i] == 1) {
                    return i;
                }
            }
            return -1;
        }
        int res = -1;
        if (count[nums[0]] == 1) {
            res = Math.Max(res, nums[0]);
        }
        if (count[nums[n - 1]] == 1) {
            res = Math.Max(res, nums[n - 1]);
        }
        return res;
    }
}
