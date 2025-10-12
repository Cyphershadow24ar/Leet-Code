// PROBLEM : (3539) Find Sum of Array Product of Magical Sequences

// SOLUTION :

#include <vector>
#include <numeric>
#include <algorithm>
#include <limits> // For __builtin_popcount if not available via <bitset> or specific headers

// Using static members for precomputed factorials and MOD value.
// This ensures they are initialized once and available across test cases without recomputation
// (as long as `max_fact_computed` is sufficient for current `m`).
long long MOD_val = 1e9 + 7;
static std::vector<long long> fact_static;
static std::vector<long long> inv_fact_static;
static int max_fact_computed = -1; // Tracks max value for which factorials are computed

// Function to calculate (base^exp) % MOD_val
long long power(long long base, long long exp) {
    long long res = 1;
    base %= MOD_val;
    while (exp > 0) {
        if (exp % 2 == 1) res = (res * base) % MOD_val;
        base = (base * base) % MOD_val;
        exp /= 2;
    }
    return res;
}

// Function to calculate modular inverse (a^-1 % MOD_val) using Fermat's Little Theorem
long long modInverse(long long n) {
    return power(n, MOD_val - 2);
}

// Precomputes factorials and inverse factorials up to max_val.
// Ensures it's only done if needed and extends existing computations.
void precompute_factorials_static(int max_val) {
    if (max_val <= max_fact_computed) {
        return; // Already computed up to this value or higher
    }

    // Resize vectors if current max_val exceeds their capacity
    if (fact_static.size() <= max_val) {
        fact_static.resize(max_val + 1);
        inv_fact_static.resize(max_val + 1);
    }
    
    // Determine where to start computing from
    int start_idx = (max_fact_computed == -1) ? 0 : max_fact_computed + 1;

    // Handle base case for factorial 0!
    if (start_idx == 0) {
        fact_static[0] = 1;
        inv_fact_static[0] = 1;
        start_idx = 1;
    }

    // Compute factorials and inverse factorials iteratively
    for (int i = start_idx; i <= max_val; ++i) {
        fact_static[i] = (fact_static[i - 1] * i) % MOD_val;
        inv_fact_static[i] = modInverse(fact_static[i]);
    }
    max_fact_computed = max_val; // Update the maximum value computed
}

class Solution {
public:
    int magicalSum(int m, int k, std::vector<int>& nums) {
        int N = nums.size(); // nums.length
        int M = m;           // seq.size
        int K = k;           // k set bits

        // Ensure factorials and inverse factorials are computed up to M
        precompute_factorials_static(M);

        // dp[i][j][c][p] stores the sum of (Product(nums[selected_indices]) * product(inv_fact[freq_idx]))
        // where elements are chosen from nums[0...i-1], and:
        //   j: total number of elements selected for `seq` so far.
        //   c: current carry.
        //   p: current number of set bits (popcount).
        // All values are modulo MOD_val.
        
        // Dimensions: (N+1) x (M+1) x (M+1) x (K+1)
        std::vector<std::vector<std::vector<std::vector<long long>>>> dp(
            N + 1,
            std::vector<std::vector<std::vector<long long>>>(
                M + 1,
                std::vector<std::vector<long long>>(
                    M + 1,
                    std::vector<long long>(K + 1, 0LL)
                )
            )
        );

        // Base case: 0 elements chosen, 0 carry, 0 popcount, product factor is 1.
        dp[0][0][0][0] = 1LL;

        // Iterate through each index `i` from `nums` (current `nums[i]` being considered)
        for (int i = 0; i < N; ++i) { 
            // Iterate through `j` (total elements picked so far)
            for (int j = 0; j <= M; ++j) {
                // Iterate through `c` (current carry)
                for (int c = 0; c <= M; ++c) {
                    // Iterate through `p` (current popcount)
                    for (int p = 0; p <= K; ++p) {
                        if (dp[i][j][c][p] == 0) {
                            continue; // Skip unreachable states
                        }

                        long long current_base_sum_products = dp[i][j][c][p];

                        // Decide how many times `nums[i]` is chosen for the sequence.
                        // `num_times` can range from 0 up to `M - j` (remaining slots for `seq`).
                        for (int num_times = 0; num_times <= M - j; ++num_times) {
                            int new_j = j + num_times; // Total elements chosen after processing `nums[i]`

                            long long val_at_bit = (long long)num_times + c;
                            int next_carry = val_at_bit / 2;
                            int new_p = p + (val_at_bit % 2);

                            // Check bounds for the new state:
                            // `new_p` (popcount) cannot exceed `K`.
                            // `next_carry` cannot exceed `M` (maximum possible carry).
                            if (new_p > K || next_carry > M) {
                                continue; 
                            }
                            
                            // Calculate term related to `nums[i]` and `num_times`:
                            // (nums[i] ^ num_times) * (1 / num_times!) % MOD_val
                            long long term_product = (power(nums[i], num_times) * inv_fact_static[num_times]) % MOD_val;

                            // Update the next DP state: Accumulate the product sum
                            long long contribution = (current_base_sum_products * term_product) % MOD_val;
                            dp[i + 1][new_j][next_carry][new_p] = (dp[i + 1][new_j][next_carry][new_p] + contribution) % MOD_val;
                        }
                    }
                }
            }
        }

        // Final step: Sum up contributions from all valid end states
        long long total_magical_sum = 0LL;
        // After processing all `N` `nums` elements, we are at `dp[N][...]`
        // We must have chosen exactly `M` elements in total (j=M)
        for (int c = 0; c <= M; ++c) { // Iterate through final carries
            for (int p = 0; p <= K; ++p) { // Iterate through final popcounts
                if (dp[N][M][c][p] == 0) {
                    continue;
                }
                // Check the total number of set bits, including those from the final carry.
                // `__builtin_popcount` is a GCC extension for counting set bits in an integer.
                // The max value of `c` (carry) is `M=30`, so `int` is sufficient for `c`.
                if (p + __builtin_popcount(c) == K) {
                    total_magical_sum = (total_magical_sum + dp[N][M][c][p]) % MOD_val;
                }
            }
        }

        // Multiply the accumulated sum by `M!`.
        // This factor `M!` corrects for the `(1/freq_idx!)` divisions done during DP,
        // effectively restoring the `m! / (product of freq_idx!)` permutation factor
        // for each multiset.
        total_magical_sum = (total_magical_sum * fact_static[M]) % MOD_val;

        return total_magical_sum;
    }
};
