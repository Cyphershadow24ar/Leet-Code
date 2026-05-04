// PROBLEM - 3304 Find the K-th Character in String Game I

class Solution {
    public char kthCharacter(int k) {
        char ch = 'a';
        int len = 1;

        // Find smallest string length >= k
        while (len < k) {
            len *= 2;
        }

        while (len > 1) {
            len /= 2;
            if (k > len) {
                k -= len;
                ch = nextChar(ch);
            }
        }

        return ch;
    }

    private char nextChar(char ch) {
        return (ch == 'z') ? 'a' : (char)(ch + 1);
    }
}
