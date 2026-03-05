// PROBLEM : (1758) Minimum Changes To Make Alternating Binary String

// SOLUTION :

class Solution {
    public int minOperations(String s) {
        int n =s.length();
        int cnt = 0;

        for(int i=0; i<n;i++ ){
            int j = s.charAt(i) - '0';
            cnt += j ^ (i % 2);
        }
        int ans = n - cnt;
        return Math.min(cnt, ans);
    }
}
