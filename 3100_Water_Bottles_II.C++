// PROBLEM : (3100) Water Bottles II

// SOLUTION :

class Solution {
public:
    int maxBottlesDrunk(int numBottles, int numExchange) {
        int drank = numBottles;   // count all initial bottles
        int empty = numBottles;   // all become empty

        while (empty >= numExchange) {
            // perform one exchange
            empty -= numExchange;   // use empty bottles for exchange
            drank += 1;             // drink the new bottle
            empty += 1;             // that bottle becomes empty
            numExchange += 1;       // cost of exchange increases
        }

        return drank;
    }
};
