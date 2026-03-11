// PROBLEM : (1009) Complement of Base 10 Integer

// SOLUTION :

class Solution {
    public int findComplement(int num) {
        if( num == 0) return 1;

        int m = 0;
        while(m > 0){
            m = (m << 1) | 1;
        }
        return m^num;
    }
}
