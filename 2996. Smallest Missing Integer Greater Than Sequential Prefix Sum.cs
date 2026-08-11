// PROBLEM : (2996) Smallest Missing Integer Greater Than Sequential Prefix Sum

// SOLUTION :

public class Solution {
    public int MissingInteger(int[] nums) {
        int n  = nums.Length;
        HashSet<int> numSet = new HashSet<int>(nums);
        int total = nums[0];

        for(int i=1; i <n; i++){
            if(nums[i] == nums[i-1] + 1){
                total += nums[i];
            }else{
                break;
            }
        }
        while(numSet.Contains(total)){
            total += 1;
        }
        return total;
    }
}
