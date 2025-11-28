// PROBLEM : (2872) Maximum Number of K-Divisible Components

// SOLUTION :

import java.util.*;

class Solution {
    private int componentCount = 0;

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        // 1. Build Adjacency List for the tree
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // 2. Start DFS from node 0 (arbitrary root)
        // Parent of root is -1
        dfs(0, -1, adj, values, k);
        
        return componentCount;
    }

    // Returns the sum of the subtree rooted at 'currentNode'
    // If a valid split is found, it returns 0
    private long dfs(int currentNode, int parentNode, List<List<Integer>> adj, int[] values, int k) {
        long sum = values[currentNode];

        for (int neighbor : adj.get(currentNode)) {
            if (neighbor != parentNode) {
                // Add sum from children (Post-Order traversal)
                sum += dfs(neighbor, currentNode, adj, values, k);
            }
        }

        // Check if the current component sum is divisible by k
        if (sum % k == 0) {
            componentCount++;
            return 0; // Cut the edge, parent sees 0 contribution
        }

        return sum; // Pass the sum up to merge with parent
    }
}
