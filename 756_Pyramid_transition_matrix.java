// PROBLEM : (756) Pyramid Transition Matrix

// SOLUTION : 

import java.util.*;

class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // Map to store allowed transitions: "AB" -> {'C', 'D'}
        Map<String, List<Character>> map = new HashMap<>();
        for (String s : allowed) {
            String base = s.substring(0, 2);
            map.computeIfAbsent(base, k -> new ArrayList<>()).add(s.charAt(2));
        }
        
        // Memoization set to store failed configurations
        Set<String> memo = new HashSet<>();
        
        return backtrack(bottom, "", map, memo);
    }

    private boolean backtrack(String current, String next, Map<String, List<Character>> map, Set<String> memo) {
        // Base case: Reached the top of the pyramid
        if (current.length() == 1) {
            return true;
        }

        // Finished building the current 'next' row, move up to the next level
        if (next.length() == current.length() - 1) {
            // Check memo: if we've processed this 'next' string before and it failed
            if (memo.contains(next)) return false;
            
            boolean result = backtrack(next, "", map, memo);
            if (!result) memo.add(next); // Cache the failure
            return result;
        }

        // Get the pair from the current row to find a top block
        int i = next.length();
        String base = current.substring(i, i + 2);

        if (map.containsKey(base)) {
            for (char top : map.get(base)) {
                // Try building the next row with this character
                if (backtrack(current, next + top, map, memo)) {
                    return true;
                }
            }
        }

        return false;
    }
}
