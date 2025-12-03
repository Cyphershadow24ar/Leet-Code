// PROBLEM : (3625) Count Number of Trapezoids II

// SOLUTION :

import java.util.*;

class Solution {
    // Helper to represent a 2D vector or slope
    static class Pair {
        int dx, dy;

        Pair(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return dx == pair.dx && dy == pair.dy;
        }

        @Override
        public int hashCode() {
            return 31 * dx + dy;
        }
    }

    public int countTrapezoids(int[][] points) {
        int n = points.length;
        
        // Map 1: Reduced Slope -> (Line Constant -> Count of Segments)
        // Used to count total trapezoid candidates (including double-counted parallelograms)
        Map<Pair, Map<Long, Integer>> slopeMap = new HashMap<>();

        // Map 2: Raw Vector -> (Line Constant -> Count of Segments)
        // Used to count parallelograms specifically
        Map<Pair, Map<Long, Integer>> vectorMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                // Normalize direction to upper-half plane to handle (A->B) same as (B->A)
                // We ensure dx > 0, or if dx == 0 then dy > 0
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }

                // 1. Handle Raw Vector (for Parallelogram counting)
                Pair rawVec = new Pair(dx, dy);
                
                // Calculate Line Constant: dx*y - dy*x
                // We must use the reduced slope for the line constant to be consistent,
                // but for raw vector grouping, using raw values for the constant 
                // is mathematically consistent within that specific bucket.
                long rawLineConst = (long) dx * points[i][1] - (long) dy * points[i][0];
                
                vectorMap.computeIfAbsent(rawVec, k -> new HashMap<>())
                         .merge(rawLineConst, 1, Integer::sum);

                // 2. Handle Reduced Slope (for Trapezoid counting)
                int g = gcd(dx, dy);
                int rdx = dx / g;
                int rdy = dy / g;
                
                Pair reducedSlope = new Pair(rdx, rdy);
                long reducedLineConst = (long) rdx * points[i][1] - (long) rdy * points[i][0];
                
                slopeMap.computeIfAbsent(reducedSlope, k -> new HashMap<>())
                        .merge(reducedLineConst, 1, Integer::sum);
            }
        }

        long totalTrapezoidCandidates = countValidPairs(slopeMap);
        long totalParallelograms = countValidPairs(vectorMap);

        // A parallelogram is counted twice in totalTrapezoidCandidates (once for each parallel pair).
        // We want to count it exactly once.
        // Current Total = (Trapezoids) + 2 * (Parallelograms)
        // We want Result = (Trapezoids) + (Parallelograms)
        // Formula: Result = Current Total - (Parallelograms)
        // Note: countValidPairs(vectorMap) returns 2 * (Actual Geometric Parallelograms) 
        // because each parallelogram has 2 pairs of equal vectors.
        // So we simply subtract half of the vector pairs count.
        
        return (int) (totalTrapezoidCandidates - (totalParallelograms / 2));
    }

    // Calculates pairs of segments that are in the same slope/vector group 
    // but lie on DIFFERENT lines (different line constants).
    private long countValidPairs(Map<Pair, Map<Long, Integer>> mainMap) {
        long validPairs = 0;
        
        for (Map<Long, Integer> lines : mainMap.values()) {
            long totalSegments = 0;
            long sumOfSquares = 0;
            
            for (int count : lines.values()) {
                totalSegments += count;
                sumOfSquares += (long) count * count;
            }
            
            // The number of pairs chosen from different lines is:
            // (Total^2 - Sum(LineCount^2)) / 2
            validPairs += (totalSegments * totalSegments - sumOfSquares) / 2;
        }
        return validPairs;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
