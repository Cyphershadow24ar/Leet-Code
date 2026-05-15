// PROBLEM : (153) Find Minimum in Rotated Sorted Array

// SOLUTION :

public class Solution {
    public int FindMin(int[] nums) {
        int left = 0;
        int right = nums.Length - 1;

        // Binary search loop
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than the rightmost element,
            // the pivot/minimum is in the right half.
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // Otherwise, the minimum is either mid or in the left half.
            else {
                right = mid;
            }
        }

        // After the loop, left == right, pointing to the minimum element.
        return nums[left];
    }
}
