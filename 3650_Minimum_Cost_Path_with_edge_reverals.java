// PROBLEM : (3650) Minimum Cost Path with Edge Reversals

// SOLUTION :
import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        // adj[u] stores {neighbor, cost}
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            // 1. Regular traversal: u -> v (cost w)
            adj[u].add(new int[]{v, w});

            // 2. Switch reversal: If you are at node v, you can reverse u -> v
            // to move v -> u (cost 2*w).
            adj[v].add(new int[]{u, 2 * w});
        }

        long[] minDist = new long[n];
        Arrays.fill(minDist, Long.MAX_VALUE);
        minDist[0] = 0;

        // Min-heap based on cost
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) { // Fixed: Changed .empty() to .isEmpty()
            long[] current = pq.poll();
            long d = current[0];
            int u = (int) current[1];

            if (d > minDist[u]) continue;
            if (u == n - 1) return (int) d;

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (minDist[u] + weight < minDist[v]) {
                    minDist[v] = minDist[u] + weight;
                    pq.offer(new long[]{minDist[v], v});
                }
            }
        }

        return minDist[n - 1] == Long.MAX_VALUE ? -1 : (int) minDist[n - 1];
    }
}
