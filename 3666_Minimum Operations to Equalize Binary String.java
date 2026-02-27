// PROBLEM : (3666) Minimum Operations to Equalize Binary String

// SOLUTION :

import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') initialZeros++;
        }

        if (initialZeros == 0) return 0;

        // Two BitSets to store unvisited states for even and odd zero-counts
        BitSet[] unvisited = new BitSet[2];
        unvisited[0] = new BitSet(n + 1);
        unvisited[1] = new BitSet(n + 1);

        for (int i = 0; i <= n; i++) {
            unvisited[i % 2].set(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(initialZeros);
        unvisited[initialZeros % 2].clear(initialZeros);
        
        int[] dist = new int[n + 1];
        dist[initialZeros] = 0;

        while (!queue.isEmpty()) {
            int z = queue.poll();
            
            // Calculate the range [minNextZ, maxNextZ]
            int minX = Math.max(0, k - (n - z)); 
            int maxX = Math.min(z, k);           
            
            // The formula nextZ = z + k - 2x 
            // x = maxX gives the smallest nextZ, x = minX gives the largest nextZ
            int low = z + k - 2 * maxX;
            int high = z + k - 2 * minX;
            int parity = (z + k) % 2;

            // Efficiently find all unvisited states in the range [low, high] with correct parity
            for (int nextZ = unvisited[parity].nextSetBit(low); 
                 nextZ != -1 && nextZ <= high; 
                 nextZ = unvisited[parity].nextSetBit(nextZ)) {
                
                if (nextZ == 0) return dist[z] + 1;
                
                dist[nextZ] = dist[z] + 1;
                queue.add(nextZ);
                unvisited[parity].clear(nextZ); // Ensure we never process this count again
            }
        }

        return -1;
    }
}
