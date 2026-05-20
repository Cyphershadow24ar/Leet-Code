// PROBLEM : (2657) Find the Prefix Common Array of Two Arrays

// SOLUTION :

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        
        // Since elements are from 1 to n, size n + 1 is sufficient
        int[] freq = new int[n + 1];
        int Count = 0;
        
        for (int i = 0; i < n; i++) {
            // Increment frequency for the element in array A
            freq[A[i]]++;
            if (freq[A[i]] == 2) {
                Count++;
            }
            
            // Increment frequency for the element in array B
            freq[B[i]]++;
            if (freq[B[i]] == 2) {
                Count++;
            }
            
            // Assign the running count to the prefix common array
            C[i] = Count;
        }
        
        return C;
    }
}
