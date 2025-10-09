// PROBLEM : (3494) Find the Minimum Amount of Time to Brew Potions

// SOLUTION :

#include <vector>
#include <numeric> // For std::accumulate (though manual sum is used)
#include <algorithm> // For std::max

class Solution {
public:
    long long minTime(std::vector<int>& skill, std::vector<int>& mana) {
        int n = skill.size(); // Number of wizards
        int m = mana.size();  // Number of potions

        // prev_potion_finish_time[i] stores the time wizard 'i' finished the *previous* potion.
        // It will be updated iteratively for each new potion.
        std::vector<long long> prev_potion_finish_time(n);

        // --- Step 1: Initialize for the first potion (j=0) ---
        // Potion 0 starts at wizard 0 at time 0.
        long long current_finish_time_for_this_potion = 0;
        for (int i = 0; i < n; ++i) {
            current_finish_time_for_this_potion += (long long)skill[i] * mana[0];
            prev_potion_finish_time[i] = current_finish_time_for_this_potion;
        }

        // --- Step 2: Iterate for subsequent potions (j=1 to m-1) ---
        for (int j = 1; j < m; ++j) {
            // 2a: Calculate S_j (Start Time of Potion j at Wizard 0)
            // S_j is the maximum of:
            // 1. When wizard 0 becomes free (prev_potion_finish_time[0])
            // 2. Ensuring no subsequent wizard 'i' is blocked by potion 'j-1' when potion 'j' arrives.
            //    Potion 'j' arrives at wizard 'i' at (S_j + cumulative_duration_to_i_minus_1).
            //    Wizard 'i' finishes potion 'j-1' at prev_potion_finish_time[i].
            //    We need (S_j + cumulative_duration_to_i_minus_1) >= prev_potion_finish_time[i].
            //    So, S_j >= prev_potion_finish_time[i] - cumulative_duration_to_i_minus_1.
            long long S_j = prev_potion_finish_time[0]; // Initialize with wizard 0's availability
            long long cumulative_duration_prefix_for_potion_j = 0; // sum(skill[x]*mana[j] for x=0 to i-1)

            for (int i = 0; i < n; ++i) {
                // At this point, cumulative_duration_prefix_for_potion_j is sum(skill[x]*mana[j] for x=0 to i-1)
                // (It's 0 for i=0).
                S_j = std::max(S_j, prev_potion_finish_time[i] - cumulative_duration_prefix_for_potion_j);
                
                // Update cumulative_duration_prefix_for_potion_j for the next iteration
                cumulative_duration_prefix_for_potion_j += (long long)skill[i] * mana[j];
            }

            // 2b: Update prev_potion_finish_time array for the current potion 'j'
            // Now, current_finish_time_for_this_potion represents the finish time of potion 'j' at wizard 'i-1'.
            // For wizard 'i', it's (finish time at i-1) + (time taken by wizard i).
            current_finish_time_for_this_potion = S_j; // Potion j starts at wizard 0 at S_j
            for (int i = 0; i < n; ++i) {
                current_finish_time_for_this_potion += (long long)skill[i] * mana[j];
                prev_potion_finish_time[i] = current_finish_time_for_this_potion;
            }
        }

        // The final result is the finish time of the last potion (m-1) by the last wizard (n-1).
        return prev_potion_finish_time[n - 1];
    }
};

