// PROBLEM : (3289) The Two Sneaky Numbers of Digitville

// SOLUTION :

#include <vector>
#include <unordered_set>
using namespace std;

class Solution {
public:
    vector<int> getSneakyNumbers(vector<int>& nums) {
        unordered_set<int> seen;
        vector<int> sneaky;

        for (int num : nums) {
            if (seen.count(num))
                sneaky.push_back(num);
            else
                seen.insert(num);
        }

        return sneaky;
    }
};
