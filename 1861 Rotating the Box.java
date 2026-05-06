// PROBLEM : (1861) Rotating the Box

// SOLUTION :

class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n  = boxGrid[0].length;

        for(int i=0; i<m; i++){
            int emp = n-1;
            for(int j=n-1; j>=0; j--){
                if(boxGrid[i][j] == '*'){
                    emp = j-1;
                }else if(boxGrid[i][j] == '#'){
                    boxGrid[i][j] = '.';
                    boxGrid[i][emp] = '#';
                    emp--;
                }
            }
        }
        char[][] rotatedBox = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotatedBox[j][m - 1 - i] = boxGrid[i][j];
            }
        }
        
        return rotatedBox;
    }
}
