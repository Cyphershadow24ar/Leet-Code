// PROBLEM : (3453) Separate Squares I

// SOLUTION :

class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double low = Double.MAX_VALUE, high = 0;

        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double target = totalArea / 2.0;

        for (int iter = 0; iter < 60; iter++) { // enough for precision
            double mid = (low + high) / 2.0;
            double areaBelow = 0;

            for (int[] sq : squares) {
                double y = sq[1];
                double l = sq[2];

                if (mid <= y) continue;
                else if (mid >= y + l) areaBelow += l * l;
                else areaBelow += l * (mid - y);
            }

            if (areaBelow < target) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
