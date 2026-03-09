// PROBLEM : (4) Median of Two Sorted Arrays

// SOLUTION :

class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length;
        int n = nums2.length;

        int low = 0, high = m;

        while (low <= high) {

            int X = (low + high) / 2;
            int Y = (m + n + 1) / 2 - X;

            int LX = (X == 0) ? Integer.MIN_VALUE : nums1[X - 1];
            int RX = (X == m) ? Integer.MAX_VALUE : nums1[X];

            int LY = (Y == 0) ? Integer.MIN_VALUE : nums2[Y - 1];
            int RY = (Y == n) ? Integer.MAX_VALUE : nums2[Y];

            if (LX <= RY && LY <= RX) {

                if ((m + n) % 2 == 0) {
                    return ((double)Math.max(LX, LY) +
                            Math.min(RX, RY)) / 2;
                } else {
                    return (double)Math.max(LX, LY);
                }

            } else if (LX > RY) {
                high = X - 1;
            } else {
                low = X + 1;
            }
        }

        return 0.0;
    }
}
