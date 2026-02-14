// PROBLEM : (799) Champagne Tower

// SOLUTION :

class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // Use a 2D array to store the amount of champagne in each glass
        // We use 102 to avoid index out of bounds for the row + 1 logic
        double[][] tower = new double[102][102];
        
        // Step 1: Pour everything into the first glass
        tower[0][0] = (double) poured;
        
        // Step 2: Simulate the flow row by row
        for (int r = 0; r <= query_row; r++) {
            for (int c = 0; c <= r; c++) {
                // If the current glass has overflow
                if (tower[r][c] > 1.0) {
                    double excess = (tower[r][c] - 1.0) / 2.0;
                    
                    // Flow to the two glasses below
                    tower[r + 1][c] += excess;
                    tower[r + 1][c + 1] += excess;
                    
                    // The current glass remains full (1.0)
                    tower[r][c] = 1.0;
                }
            }
        }
        
        // Step 3: Return the result for the specific glass
        // Note: The loop above might leave tower[query_row][query_glass] > 1 
        // if we didn't process its overflow yet, so we cap it at 1.0.
        return Math.min(1.0, tower[query_row][query_glass]);
    }
}
