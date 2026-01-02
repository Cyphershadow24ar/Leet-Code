//  PROBLEM : (961) N-Repeated Element in Size 2N Array

// SOLUTION :

class Solution {
    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums){
            if( !set.add(num)){
                return num;
            }
        }
        return -1;
    }
}
