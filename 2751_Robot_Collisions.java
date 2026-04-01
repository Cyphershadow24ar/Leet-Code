// PROBLEM : (2751) Robot Collisions

// SOLUTION :

import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));

        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int currentIndex : indices) {
            if (directions.charAt(currentIndex) == 'R') {
                stack.push(currentIndex);
            } else {
                while (!stack.isEmpty() && healths[currentIndex] > 0) {
                    int topIndex = stack.peek();

                    if (healths[topIndex] < healths[currentIndex]) {
                        healths[topIndex] = 0;
                        healths[currentIndex] -= 1;
                        stack.pop();
                    } else if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                    } else {
                        healths[topIndex] = 0;
                        healths[currentIndex] = 0;
                        stack.pop();
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                result.add(h);
            }
        }
        return result;
    }
}
