/* PROBLEM : (2438) Range Product Queries of Powers

Solution :- 
*/

class Solution {
public:
    // Helper function for modular exponentiation: (base^exp) % MOD
    long long power(long long base, long long exp) {
        long long res = 1;
        long long MOD = 1e9 + 7;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    vector<int> productQueries(int n, vector<vector<int>>& queries) {
        // Step 1: Find the exponents from the binary representation of n.
        // These correspond to the powers of 2 that sum up to n.
        vector<int> exponents;
        for (int i = 0; i < 31; ++i) { // n <= 10^9 < 2^30, so max exponent is 29.
            if ((n >> i) & 1) {
                exponents.push_back(i);
            }
        }

        // Step 2: Create a prefix sum array of the exponents for fast range sum queries.
        int k = exponents.size();
        vector<long long> prefix_sums(k + 1, 0);
        for (int i = 0; i < k; ++i) {
            prefix_sums[i + 1] = prefix_sums[i] + exponents[i];
        }

        // Step 3: Process each query.
        vector<int> answers;
        for (const auto& query : queries) {
            int left = query[0];
            int right = query[1];

            // Calculate the sum of exponents in the given range [left, right].
            long long total_exponent = prefix_sums[right + 1] - prefix_sums[left];

            // The product is 2^total_exponent. Calculate this modulo 10^9 + 7.
            long long result = power(2, total_exponent);
            
            answers.push_back(static_cast<int>(result));
        }

        return answers;
    }
};
