// PROBLEM : (1390) Four Divisors 

// SOLUTION :

class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int n : nums) {
            int sum = 0;
            int count = 0;

            for (int i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    int d1 = i;
                    int d2 = n / i;

                    if (d1 == d2) {
                        count++;
                        sum += d1;
                    } else {
                        count += 2;
                        sum += d1 + d2;
                    }

                    if (count > 4) break; // early exit
                }
            }

            if (count == 4) {
                totalSum += sum;
            }
        }

        return totalSum;
    }
}
