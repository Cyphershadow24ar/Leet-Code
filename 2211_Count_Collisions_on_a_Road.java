// PROBLEM : (2211) Count Collisions on a Road

// SOLUTION :

class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        int left = 0;
        int right = n - 1;

        // 1. Skip all cars moving Left at the very beginning
        // They drive away and never hit anything.
        while (left < n && directions.charAt(left) == 'L') {
            left++;
        }

        // 2. Skip all cars moving Right at the very end
        // They drive away and never hit anything.
        while (right >= 0 && directions.charAt(right) == 'R') {
            right--;
        }

        // 3. Count moving cars in the remaining zone
        int collisions = 0;
        for (int i = left; i <= right; i++) {
            if (directions.charAt(i) != 'S') {
                collisions++;
            }
        }

        return collisions;
    }
}
