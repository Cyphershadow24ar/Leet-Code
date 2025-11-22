// PROBLEM : (3190) Find Minimum Operations to Make All Elements Divisible by Three

// SOLUTION :

class Solution {
    public int minimumOperations(int[] nums) {
        int operations = 0;
        for(int num : nums){
            int r = num % 3;
            if( r == 1 || r == 2){
                operations += 1;
            }
        }
        return operations;
    }
}
