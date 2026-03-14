// PROBLEM: (1415) The k-th Lexicographical String of All Happy Strings of Length n

// SOLUTION : 

class Solution {
    public String getHappyString(int n, int k) {
        // Calculate the total number of happy strings possible
        // First char has 3 choices, others have 2.
        int total = 3 * (int) Math.pow(2, n - 1);
        
        if (k > total) return "";

        StringBuilder sb = new StringBuilder();
        char[] chars = {'a', 'b', 'c'};
        
        // Adjust k to be 0-indexed for easier math
        k--;

        // Determine the first character
        int partitionSize = (int) Math.pow(2, n - 1);
        int firstCharIdx = k / partitionSize;
        sb.append(chars[firstCharIdx]);
        
        // Determine the remaining n-1 characters
        for (int i = 1; i < n; i++) {
            k %= partitionSize;
            partitionSize /= 2;
            
            // Get available characters (excluding the previous one)
            char prev = sb.charAt(sb.length() - 1);
            char nextChar = ' ';
            
            int count = 0;
            for (char c : chars) {
                if (c == prev) continue;
                // If k falls within the first available option's range
                if (k < partitionSize && count == 0) {
                    nextChar = c;
                    break;
                } 
                // If k falls within the second available option's range
                else if (count == 1) {
                    nextChar = c;
                    break;
                }
                count++;
            }
            sb.append(nextChar);
        }

        return sb.toString();
    }
}
