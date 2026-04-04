// PROBLEM : (2075) Decode the Slanted Ciphertext

// SOLUTION :

class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (n == 0) return "";
        
        int cols = n / rows;
        StringBuilder sb = new StringBuilder();
        
        for (int c = 0; c < cols; c++) {
            int currRow = 0;
            int currCol = c;
            
            while (currRow < rows && currCol < cols) {
                int index = currRow * cols + currCol;
                sb.append(encodedText.charAt(index));
                
                currRow++;
                currCol++;
            }
        }
        
        int lastIdx = sb.length() - 1;
        while (lastIdx >= 0 && sb.charAt(lastIdx) == ' ') {
            lastIdx--;
        }
        
        return sb.substring(0, lastIdx + 1);
    }
}
