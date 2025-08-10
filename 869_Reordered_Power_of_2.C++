/* PROBLEM :- (869) Reordered Power of 2

Solution :-
*/

class Solution {
public:
    bool reorderedPowerOf2(int n) {
        auto signature = [](int x) {
            string s = to_string(x);
            sort(s.begin(), s.end());
            return s;
        };
        
        string sig_n = signature(n);
        static unordered_set<string> power_sigs;
        
        // Precompute once
        if (power_sigs.empty()) {
            for (int i = 1; i <= 1e9; i <<= 1) {
                power_sigs.insert(signature(i));
            }
        }
        
        return power_sigs.count(sig_n) > 0;
    }
};
