// PROBLEM : (1356) Sort Integers by The Number of 1 Bits

// SOLUTION :

import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        
        Integer[] temp = new Integer[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        
        Arrays.sort(temp, (a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            
            if (countA == countB) {
                return a - b;  // sort by value if bit count same
            }
            
            return countA - countB;  // sort by bit count
        });
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
        
        return arr;
    }
}
