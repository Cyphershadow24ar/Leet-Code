// PROBLEM : (3583) Count Special Triplets

// SOLUTION :

class Solution {
    public int specialTriplets(int[] nums) {
        int MAX_VAL = 100000;
        int[] leftFreq = new int[MAX_VAL + 1];
        int[] rightFreq = new int[MAX_VAL + 1];

        for(int num : nums){
            rightFreq[num]++;
        }

        long count = 0;
        long MOD = 1_000_000_007;

        for(int j=0; j<nums.length; j++){
            int currentVal = nums[j];
            rightFreq[currentVal]--;
            long target = (long) currentVal * 2;

            if(target <= MAX_VAL){
                long leftCount = leftFreq[(int) target];
                long rightCount = rightFreq[(int) target];

                count = ( count + (leftCount * rightCount)) % MOD;
            }
            leftFreq[currentVal]++;
        }
        return (int) count;
    }
}
