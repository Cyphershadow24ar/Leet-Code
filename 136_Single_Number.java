// PROBLEM : (136) Single Number

// SOLUTION : 

class Solution {
    public int singleNumber(int[] nums) {
        int result =0;
        for( int num : nums){
            result ^= num;
        }
        return result;
    }
}
