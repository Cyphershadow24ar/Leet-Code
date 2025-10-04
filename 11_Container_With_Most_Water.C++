// PORBLEM : (11) Container With Most Water

// SOLUTION : 

class Solution {
public:
    int maxArea(vector<int>& height) {
        int l = 0, r = height.size() - 1;
        int maxWater = 0;

        while (l < r) {
            int h = min(height[l], height[r]);
            int width = r - l;
            maxWater = max(maxWater, h * width);

            // Move the smaller height pointer
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return maxWater;
    }
};


