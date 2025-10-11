// PROBLEM : (3186) Maximum Total Damage With Spell Casting

// solution :

#include <vector>
#include <map>
#include <algorithm>
#include <iterator>

class Solution {
public:
    long long maximumTotalDamage(std::vector<int>& power) {
        // Step 1: Count frequencies of each power value.
        // A std::map automatically stores unique powers in sorted order.
        std::map<int, int> counts;
        for (int p : power) {
            counts[p]++;
        }
        
        // Step 2: Dynamic Programming.
        // We use a map `dp` where dp[p] will store the maximum damage
        // achievable considering all unique spell powers up to p.
        // The values in this map will be non-decreasing.
        std::map<int, long long> dp;
        long long max_damage_so_far = 0;
        
        // Iterate through the unique powers in increasing order.
        for (auto const& [p, count] : counts) {
            // Option 1: Don't cast spells with power `p`.
            // The maximum damage is the same as the max damage from powers less than `p`.
            // This is the value we calculated in the previous iteration.
            long long option1_skip = max_damage_so_far;
            
            // Option 2: Cast all spells with power `p`.
            // The damage gained from this is p * count.
            // We can add the maximum damage from compatible spells, i.e., those
            // with power less than p - 2.
            long long option2_take = (long long)p * count;
            
            // Find the largest unique power p' < p - 2.
            // `dp.lower_bound(p - 2)` finds the first element with key >= p - 2.
            auto it = dp.lower_bound(p - 2);
            
            // If `it` is not the beginning, then `std::prev(it)` gives us an
            // iterator to the element with the largest key that is < p - 2.
            // The value associated with this key is the max damage up to that point.
            if (it != dp.begin()) {
                auto prev_it = std::prev(it);
                option2_take += prev_it->second;
            }
            
            // The new maximum damage is the best of the two options.
            max_damage_so_far = std::max(option1_skip, option2_take);
            dp[p] = max_damage_so_far;
        }
        
        return max_damage_so_far;
    }
};
