// PROBLEM : (3474) Lexicographically Smallest Generated String

// SOLUTION :

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;
        char[] res = new char[L];
        boolean[] fixed = new boolean[L];

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (fixed[i + j] && res[i + j] != str2.charAt(j)) {
                        return ""; 
                    }
                    res[i + j] = str2.charAt(j);
                    fixed[i + j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != str2.charAt(j)) return "";
                }
            }
        }

        for (int i = 0; i < L; i++) {
            if (fixed[i]) continue;

            for (char c = 'a'; c <= 'z'; c++) {
                res[i] = c;
                if (isValid(res, fixed, i, str1, str2)) {
                    fixed[i] = true;
                    break;
                }
            }
            if (!fixed[i]) return ""; 
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                if (isMatch(res, i, str2)) return "";
            }
        }

        return new String(res);
    }

    private boolean isValid(char[] res, boolean[] fixed, int currIdx, String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        for (int i = Math.max(0, currIdx - m + 1); i <= Math.min(currIdx, n - 1); i++) {
            if (str1.charAt(i) == 'F') {
                boolean fullyFilled = true;
                for (int j = 0; j < m; j++) {
                    if (!fixed[i + j] && (i + j) != currIdx) {
                        fullyFilled = false;
                        break;
                    }
                }
                
                if (fullyFilled && isMatch(res, i, str2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isMatch(char[] res, int start, String str2) {
        for (int j = 0; j < str2.length(); j++) {
            if (res[start + j] != str2.charAt(j)) return false;
        }
        return true;
    }
}
