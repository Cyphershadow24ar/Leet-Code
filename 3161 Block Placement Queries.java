// PROBLEM : (3161) Block Placement Queries
 
// SOLUTION :

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Solution {
    
    // Iterative Segment Tree for fast point updates and range maximum queries
    class SegTree {
        int[] tree;
        int n;
        
        SegTree(int n) {
            this.n = n;
            tree = new int[2 * n];
        }
        
        // Point update: set value at index p
        void update(int p, int value) {
            for (tree[p += n] = value; p > 1; p >>= 1) {
                tree[p >> 1] = Math.max(tree[p], tree[p ^ 1]);
            }
        }
        
        // Range query: get max in [l, r)
        int query(int l, int r) {
            int res = 0;
            for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1) res = Math.max(res, tree[l++]);
                if ((r & 1) == 1) res = Math.max(res, tree[--r]);
            }
            return res;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        // Find the maximum coordinate 'x' to appropriately size the segment tree
        int maxX = 0;
        for (int[] q : queries) {
            if (q[1] > maxX) {
                maxX = q[1];
            }
        }
        
        SegTree st = new SegTree(maxX + 1);
        TreeSet<Integer> obstacles = new TreeSet<>();
        
        // The origin acts as the permanent starting boundary
        obstacles.add(0);
        
        List<Boolean> ans = new ArrayList<>();
        
        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];
                Integer L = obstacles.lower(x);
                Integer R = obstacles.higher(x);
                
                obstacles.add(x);
                
                // The gap for the newly added obstacle at x
                st.update(x, x - L);
                
                // If there's an obstacle ahead, its gap is shortened 
                if (R != null) {
                    st.update(R, R - x);
                }
            } else {
                int x = q[1];
                int sz = q[2];
                
                // Greatest obstacle coordinate that is <= x
                Integer L = obstacles.floor(x);
                
                // The maximum block we can place is bounded by either:
                // 1) The max gap natively existing between obstacles <= L
                // 2) The gap falling from the last obstacle L up to the boundary x
                int maxGap = Math.max(st.query(0, L + 1), x - L);
                
                ans.add(maxGap >= sz);
            }
        }
        
        return ans;
    }
}
