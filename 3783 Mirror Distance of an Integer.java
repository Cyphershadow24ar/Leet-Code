// PROBLEM : (3783) Mirror Distance of an Integer

// SOLUTION :

class Solution {
    public int mirrorDistance(int n) {
        int og = n;
        long rev = 0;
        while (n != 0) {
            int digit = n % 10;
            rev= rev * 10 + digit;
            n /= 10;
        }

        int ans  = Math.abs(og - (int)rev);

        return ans;
    }
}
