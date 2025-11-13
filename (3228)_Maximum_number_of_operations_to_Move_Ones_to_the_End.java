// PROBLEM : (3228) Maximum Number of Operations to Move Ones to the End

// SOLUTION :

public class Solution {

    public int maxOperations(String s) {
        int countOne = 0;
        int ans = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '0') {
                while (i + 1 < s.length() && s.charAt(i + 1) == '0') {
                    i++;
                }
                ans += countOne;
            } else {
                countOne++;
            }
            i++;
        }
        return ans;
    }
}
