// PROBLEM : (2147) Number of Ways to Divide a Long Corridor

// SOLUTION :

class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = 1000_000_007;
        long result = 1;
        int prevSeatIndex = 0;
        int numSeats = 0;

        for(int i = 0; i < corridor.length(); i++){
            char c = corridor.charAt(i);
            if(c == 'S'){
                numSeats++;
                if(numSeats > 2 && numSeats % 2 == 1){
                    result = result * (i - prevSeatIndex)%MOD;
                }
                prevSeatIndex = i;
            }
        }

        return numSeats > 1 && numSeats % 2 == 0 ? (int)result : 0;
    }
}
