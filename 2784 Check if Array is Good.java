// PROBLEM : (2784) Check if Array is Good

// SOLUTION :

class Solution {
    public boolean isGood(int[] nums) {
        int maxval = 0;
        for(int num : nums){
            if(num > maxval) maxval = num;
        }

        if(nums.length != maxval + 1){ 
            return false;
        }

        int[] count = new int[maxval + 1];
        for(int num: nums){
            count[num]++;
        }

        for(int  i=1;i<maxval; i++){
            if(count[i] != 1){
                return false;
            }
        }
        return  count[maxval] == 2;
    }
}
