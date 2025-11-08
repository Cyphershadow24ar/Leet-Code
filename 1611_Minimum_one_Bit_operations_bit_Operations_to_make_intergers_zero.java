// PROBLEM : (1611) Minimum ONe bit Operations to Make intergers Zero

// SOLUTION :

class Solution {
    public int minimumOneBitOperations(int n) {
        if(n == 0) return 0;
        int k =0;
        while((1 << (k+1)) <= n) k++;
        return ((1 << (k + 1)) - 1) - minimumOneBitOperations(n ^(1 << k));
    }
}
