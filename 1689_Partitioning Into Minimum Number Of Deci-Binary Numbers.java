// PROBLEM : (1689) Partitioning Into Minimum Number Of Deci-Binary Numbers

// SOLUTION :

class Solution {
    public int minPartitions(String n) {
        int max = 0;
        for(int i=0; i<n.length(); i++){
            int digit =n.charAt(i) - '0';
            max = Math.max(max, digit);

            if(max == 9) return 9;
        }
        return max;
    }
}
