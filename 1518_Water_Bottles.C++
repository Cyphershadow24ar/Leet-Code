// PROBLEM : (1518) Water Bottles

// SOLUTION : 

class Solution {
public:
    int numWaterBottles(int numBottles, int numExchange) {
        int total = numBottles;
        int empty = numBottles;

        while (empty >= numExchange) {
            int newBottles = empty / numExchange;   // how many new full bottles we get
            total += newBottles;                    // add to total drinks
            empty = empty % numExchange + newBottles; // leftover empty + new empty
        }

        return total;
    }
};
