// PROBLEM : (3577) Count the Number of Computer Unlocking Permutations

// SOLUTION :

class Solution {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        int rootComplexity = complexity[0];
        long MOD = 1_000_000_007;

        for(int i=1; i<n; i++){
            if(complexity[i] <= rootComplexity){
                return 0;
            }
        }
        long ans = 1;
        for(int i = 1; i<n; i++){
        ans = (ans * i)% MOD;
        }
        return (int) ans;
    }
}
