// PROBLEM : (136) Single Number

// SOLUTION : 

// 1. Using Bit Manipulation 

class Solution {
    public int singleNumber(int[] nums) {
        int result =0;
        for( int num : nums){
            result ^= num;
        }
        return result;
    }
}


// 2. Linear Search 

class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        
        for(int i =0; i<n-1; i+=2){
            if(nums[i] != nums[i+1]){ 
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }
}
