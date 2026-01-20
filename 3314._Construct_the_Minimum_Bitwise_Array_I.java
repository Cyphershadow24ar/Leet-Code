// PROBLEM : (3314) Construct the Minimum Bitwise Array I

// SOLUTION :

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);

            // Special case: 2 cannot be formed
            if (num == 2) {
                ans[i] = -1;
                continue;
            }

            // Count trailing 1s in binary representation of num
            int temp = num;
            int trailingOnes = 0;
            while ((temp & 1) == 1) {
                trailingOnes++;
                temp >>= 1;
            }

            // Compute minimum ans[i]
            ans[i] = num - (1 << (trailingOnes - 1));
        }

        return ans;
    }
}
