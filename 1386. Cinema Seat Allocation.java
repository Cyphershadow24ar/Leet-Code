// PROBLEM : (1386) Cinema Seat Allocation

// SOLUTION :

import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats using a bitmask for each row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Only seats 2 to 9 can affect the answer
            if (col >= 2 && col <= 9) {
                int mask = map.getOrDefault(row, 0);
                mask |= (1 << col);
                map.put(row, mask);
            }
        }

        // Every completely empty row can fit 2 groups
        long ans = (long) (n - map.size()) * 2;

        for (int mask : map.values()) {

            // Seats 2,3,4,5
            boolean left = (mask & ((1 << 2) | (1 << 3) | 
                                   (1 << 4) | (1 << 5))) == 0;

            // Seats 4,5,6,7
            boolean middle = (mask & ((1 << 4) | (1 << 5) | 
                                      (1 << 6) | (1 << 7))) == 0;

            // Seats 6,7,8,9
            boolean right = (mask & ((1 << 6) | (1 << 7) | 
                                     (1 << 8) | (1 << 9))) == 0;

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return (int) ans;
    }
}
