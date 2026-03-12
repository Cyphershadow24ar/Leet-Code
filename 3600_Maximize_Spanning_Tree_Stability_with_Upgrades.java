// PROBLEM : (3600) Maximize Spanning Tree Stability with Upgrades

// SOLUTION :

import java.util.*;

class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        int low = 1, high = 200000;
        int ans = -1;

        // Pre-check: Mandatory edges must not form a cycle and must be <= n-1
        if (!checkMandatoryAcyclic(n, edges)) return -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canFormST(n, edges, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean checkMandatoryAcyclic(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int mandatoryCount = 0;
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (!dsu.union(e[0], e[1])) return false; // Cycle found
                mandatoryCount++;
            }
        }
        return mandatoryCount < n; 
    }

    private boolean canFormST(int n, int[][] edges, int k, int target) {
        DSU dsu = new DSU(n);
        int edgesUsed = 0;
        int upgradesUsed = 0;

        // 1. Must include all mandatory edges. They must all be >= target.
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < target) return false; 
                dsu.union(e[0], e[1]);
                edgesUsed++;
            }
        }

        // 2. Use edges that are already >= target (No upgrade cost)
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= target) {
                if (dsu.union(e[0], e[1])) {
                    edgesUsed++;
                }
            }
        }

        // 3. Use edges that need an upgrade to reach target (Cost 1 upgrade)
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < target && e[2] * 2 >= target) {
                if (upgradesUsed < k) {
                    if (dsu.union(e[0], e[1])) {
                        edgesUsed++;
                        upgradesUsed++;
                    }
                }
            }
        }

        return dsu.components == 1;
    }

    // Standard Disjoint Set Union (DSU) for connectivity
    class DSU {
        int[] parent;
        int components;
        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
            components = n;
        }
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                components--;
                return true;
            }
            return false;
        }
    }
}
