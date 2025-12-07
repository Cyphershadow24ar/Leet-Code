// PROBLEM : (1523) Count Odd Numbers in an Interval Range

// SOLUTION :

class Solution {
    public int countOdds(int low, int high) {
        // Calculate odds up to high and subtract odds before low
        return (high + 1) / 2 - low / 2;
    }
}
