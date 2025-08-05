// PROBLEM :- 3477 Fruits Into Baskets II

// SOLUTION :-

class Solution {
public:
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        int n = fruits.size();
        vector<bool> used(n, false);
        int Upf = 0; 
        // unplaced fruit

        for(int i=0; i<n; ++i){
            bool pld = false; 
            // placed

            for(int j =0; j<n; ++j){
                if(!used[j] && baskets[j] >= fruits[i]){
                    used[j] = true;
                    pld = true;
                    break;
                }
            }
            if(!pld) Upf++;
        }
        return Upf;
    }
};
