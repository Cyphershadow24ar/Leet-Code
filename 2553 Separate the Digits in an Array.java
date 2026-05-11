// PROBLEM : (2553) Separate the Digits in an Array

// SOLUTION :

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> digitList = new ArrayList<>();
        
        // Use a standard index-based loop to iterate through the input array
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            
            // Convert the number to a string to easily access digits in order
            String s = Integer.toString(currentNum);
            
            // Use a standard index-based loop to iterate through the characters of the string
            for (int j = 0; j < s.length(); j++) {
                // Character.getNumericValue converts '1' to 1
                digitList.add(Character.getNumericValue(s.charAt(j)));
            }
        }
        
        // Create the final primitive array
        int[] result = new int[digitList.size()];
        
        // Use a standard loop to move items from the List to the int array
        for (int k = 0; k < digitList.size(); k++) {
            result[k] = digitList.get(k);
        }
        
        return result;
    }
}
