// PROBLEM : (1653) Minimum Deletions to Make String Balanced

// SOLUITON :

class Solution {
    public int minimumDeletions(String s) {
        int deletions = 0;
        int bCount = 0;
        
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                // We found a 'b'. We don't delete it yet, 
                // just keep track of it.
                bCount++;
            } else {
                // We found an 'a'. 
                // Option 1: Delete this 'a' (deletions + 1)
                // Option 2: Keep this 'a' but remove a previous 'b' (bCount)
                // We take the minimum to stay optimal.
                deletions = Math.min(deletions + 1, bCount);
            }
        }
        
        return deletions;
    }
}
