// PROBLEM : (2197) Replace Non-Coprime Numbers in Array

// SOLUTION :

#include <vector>
#include <numeric>  // for gcd
using namespace std;

class Solution {
public:
    vector<int> replaceNonCoprimes(vector<int>& nums) {
        vector<int> st;  // stack simulation

        for (int x : nums) {
            st.push_back(x);

            // Keep merging as long as last two are non-coprime
            while (st.size() > 1) {
                int a = st.back(); st.pop_back();
                int b = st.back(); st.pop_back();
                int g = gcd(a, b);

                if (g > 1) {  
                    // merge into lcm
                    long long lcm = (1LL * a / g) * b;
                    st.push_back((int)lcm);
                } else {
                    // they are coprime → put both back
                    st.push_back(b);
                    st.push_back(a);
                    break;
                }
            }
        }
        return st;
    }
};
