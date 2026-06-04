// PROBLEM :  (3751) Total Waviness of Numbers in Range I

// SOLUTION :

class Solution {
    public int totalWaviness(int num1, int num2) {
        int ans = 0;

        for (int x = num1; x <= num2; x++) {
            ans += waviness(x);
        }

        return ans;
    }

    private int waviness(int num) {
        String s = String.valueOf(num);
        int n = s.length();

        if (n < 3) return 0;

        int cnt = 0;

        for (int i = 1; i < n - 1; i++) {
            char l = s.charAt(i - 1);
            char c = s.charAt(i);
            char r = s.charAt(i + 1);

            if ((c > l && c > r) || (c < l && c < r)) {
                cnt++;
            }
        }

        return cnt;
    }
}
