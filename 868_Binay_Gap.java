// PROBLEM : (868) Binary Gap

// SOLUTION :

class Solution {
    public int binaryGap(int n) {
        int prevIndex = -1;   // Stores position of previous 1
        int maxDistance = 0;
        int currentIndex = 0;
        
        while (n > 0) {
            if ((n & 1) == 1) {   // If last bit is 1
                if (prevIndex != -1) {
                    maxDistance = Math.max(maxDistance, currentIndex - prevIndex);
                }
                prevIndex = currentIndex;
            }
            
            n = n >> 1;   // Right shift
            currentIndex++;
        }
        
        return maxDistance;
    }
}
