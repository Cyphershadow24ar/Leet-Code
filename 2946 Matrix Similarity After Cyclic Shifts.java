// PROBLEM : (2946) Matrix Similarity After Cyclic Shifts

// SOLUTION :

class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        k = k % n;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i  % 2 == 0){
                    if(mat[i][j] != mat[i][(j + k) % n]){
                        return false;
                    }
                }else{
                    if(mat[i][j] != mat[i][(j - k + n) % n]){
                        return false;
                    }
                }
            }
        }  
        return true; 
    }
}
