// PROBLEM : (3074) Apple Redistribution into Boxes

// SOLUTION :

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }

        // Sort capacities in descending order
        Arrays.sort(capacity);
        
        int usedBoxes = 0;
        int currentCapacity = 0;

        // Pick boxes from largest to smallest
        for (int i = capacity.length - 1; i >= 0; i--) {
            currentCapacity += capacity[i];
            usedBoxes++;
            if (currentCapacity >= totalApples) {
                return usedBoxes;
            }
        }

        return usedBoxes; // guaranteed possible by constraints
    }
}
