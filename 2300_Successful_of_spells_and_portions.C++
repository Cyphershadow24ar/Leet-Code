// PROBLEM : 2300 Successful of spells and portions

// SOLUTION : 

#include <vector>
#include <algorithm> // Required for std::sort and std::lower_bound
#include <cmath>     // Not strictly needed with integer ceil trick

class Solution {
public:
    std::vector<int> successfulPairs(std::vector<int>& spells, std::vector<int>& potions, long long success) {
        // Step 1: Sort the potions array in ascending order.
        // This allows us to use binary search for efficient lookup.
        std::sort(potions.begin(), potions.end());

        int n = spells.size();
        int m = potions.size();
        std::vector<int> pairs(n); // To store the results for each spell

        // Step 2: Iterate through each spell
        for (int i = 0; i < n; ++i) {
            long long spell_strength = spells[i]; // Use long long to avoid overflow in calculations

            // Step 2a: Calculate the minimum required potion strength
            // We need a potion_strength 'P' such that spell_strength * P >= success
            // This implies P >= success / spell_strength
            // Since P must be an integer, we need P >= ceil(success / spell_strength)
            // For positive integers A and B, ceil(A/B) can be calculated as (A + B - 1) / B
            long long min_required_potion_strength = (success + spell_strength - 1) / spell_strength;

            // Step 2b: Use binary search (std::lower_bound) to find the first potion
            // that meets or exceeds the min_required_potion_strength.
            // std::lower_bound returns an iterator to the first element in the range
            // [potions.begin(), potions.end()) that is not less than min_required_potion_strength.
            auto it = std::lower_bound(potions.begin(), potions.end(), min_required_potion_strength);

            // Step 2c: The number of successful potions is the count from 'it' to the end of 'potions' array.
            // std::distance calculates the number of elements between two iterators.
            pairs[i] = std::distance(it, potions.end());
        }

        return pairs;
    }
};
