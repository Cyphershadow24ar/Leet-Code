// PROBLEM : (3702) Longest Subsequence With Non-Zero Bitwise XOR

// SOLUTION :

public class Solution {
    public int LongestSubsequence(int[] nums) {
        int xorsum = 0 ;
        bool allZero = true;
        foreach (int num in nums){
            xorsum ^= num;
            if (num != 0) allZero = false;

        }
        if(allZero) return 0;
        return xorsum != 0 ? nums.Length : nums.Length - 1;
    }
}
