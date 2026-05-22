// PROBLME : (33) Search in Rotated Sorted Array

// SOLUTION :

class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int h = n-1;

        while(l < h){
            int mid = (l+h)/2;
            
            if(nums[mid] > nums[n-1]) l = mid +1;
            else h = mid; // for moving low and high in the nums array.
        }

        int rot = l;
        l = 0;
        h = n-1;

        while(l <= h){
            int mid = (l + h)/2;
            int real = (mid + rot) % n;

            if(nums[real] == target)
                return real;

            if(nums[real] < target) l = mid + 1;
            else h = mid - 1;
        }
        return -1;
    }
}
