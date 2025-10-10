// PROBLEM : (3147) Taking Maximum Energy From the Mystic Dungeon

// SOLUTOIN: 

#include <vector>
#include <algorithm> // Required for std::max
#include <limits>    // Required for std::numeric_limits<int>::min()

class Solution {
public:
    int maximumEnergy(std::vector<int>& energy, int k) {
        int n = energy.size();
        // Initialize ans with the smallest possible integer value,
        // to correctly handle cases where all path sums are negative.
        int ans = std::numeric_limits<int>::min();

        // The outer loop iterates through the last 'k' indices of the energy array.
        // Each `i` in this range corresponds to the "farthest" possible magician
        // in one of the `k` distinct k-jump sequences.
        for (int i = n - k; i < n; i++) {
            // `sum` will accumulate energy for a path starting at `j` and ending at `i`.
            int sum = 0;
            
            // The inner loop iterates backward along the k-jump sequence
            // that contains magician `i`. `j` represents a potential starting point
            // for a path ending at `i`.
            for (int j = i; j >= 0; j -= k) {
                // Add the energy of the current magician `j` to the `sum`.
                // At this point, `sum` holds the total energy for the path
                // that starts at `j` and follows k-jumps until `i`.
                sum += energy[j];
                
                // Update the overall maximum energy found so far.
                // This ensures we capture the maximum for any valid path,
                // regardless of its starting point.
                ans = std::max(ans, sum);
            }
        }
        return ans;
    }
};
