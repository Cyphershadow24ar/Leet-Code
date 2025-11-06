// PROBLEM : (3607) Power Grid Maintenance 

// SOLUTION :

import java.util.*;

class Solution {
    static class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return;
            if (rank[px] < rank[py]) parent[px] = py;
            else if (rank[py] < rank[px]) parent[py] = px;
            else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);

        // Step 1: Union all connections
        for (int[] con : connections) {
            dsu.union(con[0], con[1]);
        }

        // Step 2: Build online station sets per grid
        Map<Integer, TreeSet<Integer>> gridMap = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            int root = dsu.find(i);
            gridMap.computeIfAbsent(root, k -> new TreeSet<>()).add(i);
        }

        boolean[] offline = new boolean[c + 1];
        List<Integer> ans = new ArrayList<>();

        // Step 3: Process queries
        for (int[] q : queries) {
            int type = q[0], x = q[1];
            int root = dsu.find(x);

            if (type == 1) {
                if (!offline[x]) {
                    ans.add(x);
                } else {
                    TreeSet<Integer> onlineSet = gridMap.get(root);
                    if (onlineSet == null || onlineSet.isEmpty())
                        ans.add(-1);
                    else
                        ans.add(onlineSet.first());
                }
            } else if (type == 2) {
                if (!offline[x]) {
                    offline[x] = true;
                    gridMap.get(root).remove(x);
                }
            }
        }

        // Convert to int array
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }
}

