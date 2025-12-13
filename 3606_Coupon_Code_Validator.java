// PROBLEM : (3606) Coupon Code Validator

// SOLUTION :

import java.util.*;

class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        // Map to store valid coupons grouped by business line
        Map<String, List<String>> validCoupons = new HashMap<>();
        
        // Initialize lists for each business line
        validCoupons.put("electronics", new ArrayList<>());
        validCoupons.put("grocery", new ArrayList<>());
        validCoupons.put("pharmacy", new ArrayList<>());
        validCoupons.put("restaurant", new ArrayList<>());
        
        // Check each coupon
        for (int i = 0; i < code.length; i++) {
            // Check all validation conditions
            if (isValidCode(code[i]) && 
                isValidBusinessLine(businessLine[i]) && 
                isActive[i]) {
                
                // Add to the appropriate business line list
                validCoupons.get(businessLine[i]).add(code[i]);
            }
        }
        
        // Prepare result list in the required order
        List<String> result = new ArrayList<>();
        
        // Add coupons in the specified order: electronics, grocery, pharmacy, restaurant
        String[] businessOrder = {"electronics", "grocery", "pharmacy", "restaurant"};
        
        for (String business : businessOrder) {
            List<String> coupons = validCoupons.get(business);
            if (!coupons.isEmpty()) {
                Collections.sort(coupons); // Sort codes lexicographically
                result.addAll(coupons);
            }
        }
        
        return result;
    }
    
    private boolean isValidCode(String code) {
        if (code == null || code.isEmpty()) {
            return false;
        }
        
        // Check if code contains only alphanumeric characters and underscores
        for (char c : code.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidBusinessLine(String businessLine) {
        return businessLine.equals("electronics") || 
               businessLine.equals("grocery") || 
               businessLine.equals("pharmacy") || 
               businessLine.equals("restaurant");
    }
}
