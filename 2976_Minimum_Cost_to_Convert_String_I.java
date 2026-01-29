// PROBLEM : (2976) Minimum Cost to Convert String I

// SOLUTION :

class Solution {
    public long minimumCost(String source, String target,
                            char[] original, char[] changed, int[] cost) {

        final long INF = (long) 1e18;
        int n = source.length();

        // Step 1: distance matrix for 26 characters
        long[][] dist = new long[26][26];

        // Step 2: initialize distances
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        // Step 3: fill direct transformations
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        // Step 4: Floyd–Warshall
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Step 5: compute total cost
        long totalCost = 0;
        for (int i = 0; i < n; i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';

            if (dist[s][t] == INF) return -1;
            totalCost += dist[s][t];
        }

        return totalCost;
    }
}
