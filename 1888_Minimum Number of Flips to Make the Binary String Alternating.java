// PROBLEM : (1888) Minimum Number of Flips to Make the Binary String Alternating

// SOLUTION :

class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String d = s + s; 

        int diff1 = 0; 
        int diff2 = 0; 
        int minf = Integer.MAX_VALUE;

        for (int i = 0; i < d.length(); i++) {
            
            if (d.charAt(i) != (char)((i % 2) + '0')) diff1++;
            if (d.charAt(i) != (char)(((i + 1) % 2) + '0')) diff2++;

            if (i >= n) {
                if (d.charAt(i - n) != (char)((i - n) % 2 + '0')) diff1--;
                if (d.charAt(i - n) != (char)((i - n + 1) % 2 + '0')) diff2--;
            }

            if (i >= n - 1) {
                minf = Math.min(minf, Math.min(diff1, diff2));
            }
        }
        return minf;
    }
}
