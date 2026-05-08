// PROBLEM : (3629) Minimum Jumps to Reach End via Prime Teleportation

// SOLUTION :

import java.util.*;

class Solution {
    private static final int MAX = 1000000;
    private static int[] spf = new int[MAX + 1];

    // Static block to precompute SPF once for all test cases
    static {
        for (int i = 2; i <= MAX; i++) spf[i] = i;
        for (int i = 2; i * i <= MAX; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (spf[j] == j) spf[j] = i;
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Map primes to indices that are multiples of that prime
        List<Integer>[] primeToIndices = new ArrayList[MAX + 1];
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            while (temp > 1) {
                int p = spf[temp];
                if (primeToIndices[p] == null) primeToIndices[p] = new ArrayList<>();
                primeToIndices[p].add(i);
                // Skip other instances of the same prime factor in this number
                while (temp % p == 0) temp /= p;
            }
        }

        // BFS setup
        Queue<Integer> queue = new ArrayDeque<>();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        boolean[] usedPrime = new boolean[MAX + 1];

        queue.offer(0);
        dist[0] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            int d = dist[u];

            if (u == n - 1) return d;

            // 1. Adjacent Step: i + 1
            if (u + 1 < n && dist[u + 1] == -1) {
                dist[u + 1] = d + 1;
                queue.offer(u + 1);
            }

            // 2. Adjacent Step: i - 1
            if (u - 1 >= 0 && dist[u - 1] == -1) {
                dist[u - 1] = d + 1;
                queue.offer(u - 1);
            }

            // 3. Prime Teleportation
            int val = nums[u];
            // Check if nums[u] is prime (val > 1 and its smallest factor is itself)
            if (val > 1 && spf[val] == val) {
                if (!usedPrime[val]) {
                    usedPrime[val] = true;
                    if (primeToIndices[val] != null) {
                        for (int v : primeToIndices[val]) {
                            if (dist[v] == -1) {
                                dist[v] = d + 1;
                                queue.offer(v);
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }
}
