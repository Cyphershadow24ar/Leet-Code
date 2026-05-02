// PROBLEM : (788) Rotated Digits

// SOLUTION :

class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isGood(int num) {
        boolean hasRotatableDigit = false;
        
        while (num > 0) {
            int digit = num % 10;
            
            // If it contains any of these, the rotation is invalid
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            
            // Check if it contains at least one digit that changes the value
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasRotatableDigit = true;
            }
            
            num /= 10;
        }
        
        // It's good only if it's valid AND different from original
        return hasRotatableDigit;
    }
}
