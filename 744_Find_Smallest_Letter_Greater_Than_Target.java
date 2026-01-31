// PROBLEM : (744) Find Smallest Letter Greater Than Target

// SOLUTION :

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0, high = letters.length - 1;
        char ans = letters[0]; // default for wrap-around case

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (letters[mid] > target) {
                ans = letters[mid];   // potential answer
                high = mid - 1;       // search left for smaller valid
            } else {
                low = mid + 1;        // search right
            }
        }

        return ans;
    }
}
