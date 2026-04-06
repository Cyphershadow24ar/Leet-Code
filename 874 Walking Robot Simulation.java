// PROBLEM : (874) Walking Robot Simulation

// SOLUTION :

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Direction vectors mapping to: North (0), East (1), South (2), West (3)
        // North means +Y, East means +X, South means -Y, West means -X
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        int d = 0; // Starts facing North
        int x = 0, y = 0;
        int maxDistSq = 0;

        // Use a Set to store obstacles for O(1) lookups.
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            // Pack the two 32-bit integers into a single 64-bit Long
            obstacleSet.add(((long) obs[0] << 32) | (obs[1] & 0xFFFFFFFFL));
        }

        for (int cmd : commands) {
            if (cmd == -2) {
                // Turn left (-90 degrees)
                d = (d + 3) % 4;
            } else if (cmd == -1) {
                // Turn right (+90 degrees)
                d = (d + 1) % 4;
            } else {
                // Move forward step by step
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dirs[d][0];
                    int ny = y + dirs[d][1];
                    
                    // Encode the next coordinate to check against obstacles
                    long nextHash = ((long) nx << 32) | (ny & 0xFFFFFFFFL);
                    
                    if (obstacleSet.contains(nextHash)) {
                        break; // Hit an obstacle, stop moving forward
                    }
                    
                    // Move to the valid cell
                    x = nx;
                    y = ny;
                    
                    // Keep track of the maximum distance squared
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }
        
        return maxDistSq;
    }
}
