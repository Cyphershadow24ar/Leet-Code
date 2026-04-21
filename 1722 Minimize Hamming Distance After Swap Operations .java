// PROBLEM : (1722) Minimize Hamming Distance After Swap Operations 

// SOLUTION :

import java.util.*;

class Solution {
    
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        
        int n = source.length;
        
        // Step 1: DSU init
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        
        // Step 2: Union
        for(int i = 0; i < allowedSwaps.length; i++){
            union(parent, allowedSwaps[i][0], allowedSwaps[i][1]);
        }
        
        // Step 3: Group indices
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        
        for(int i = 0; i < n; i++){
            int root = find(parent, i);
            
            if(!map.containsKey(root)){
                map.put(root, new HashMap<>());
            }
            
            HashMap<Integer, Integer> freq = map.get(root);
            
            int val = source[i];
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }
        
        // Step 4: Match target
        int ans = 0;
        
        for(int i = 0; i < n; i++){
            int root = find(parent, i);
            HashMap<Integer, Integer> freq = map.get(root);
            
            int val = target[i];
            
            if(freq.containsKey(val) && freq.get(val) > 0){
                freq.put(val, freq.get(val) - 1);
            } else {
                ans++;
            }
        }
        
        return ans;
    }
    
    int find(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    
    void union(int[] parent, int a, int b){
        int pa = find(parent, a);
        int pb = find(parent, b);
        if(pa != pb){
            parent[pa] = pb;
        }
    }
}
