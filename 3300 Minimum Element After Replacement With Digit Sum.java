// PROBLEM : (3300) Minimum Element After Replacement With Digit Sum

// SOLUTION :

class Solution {
    public int minElement(int[] nums) {
        int n = nums.length;
        // int digitsum = 0;
        int[] freq = new int[n];


        for(int i= 0 ;i<n; i++){
            int num = Math.abs(nums[i]);
            int digitsum  = 0;
            while(num > 0){
                digitsum += num % 10;
                num /= 10;
            }
            freq[i] = digitsum;
        }

        Arrays.sort(freq);
        return freq[0];
    }
}
