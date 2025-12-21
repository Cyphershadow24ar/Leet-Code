// PROBLEM : (1) TWO SUM

// SOLUTION :

class Solution {
    public int[] twoSum(int[] nums, int target) {
       
       for(int i = 1 ; i < nums.length; i++){
        for(int j = 0 ; j <  nums.length - i; j++){
            if(nums[j] + nums[j+i] == target)
            {
                return new int[]{j,j + i};
            }
        }
       }
    return new int[]{};
    }
}
