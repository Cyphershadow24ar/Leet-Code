// PROBLEM : (3000) Maximum Area of Longest Diagonal Rectangle

//SOLUTION :

class Solution {
public:
    int areaOfMaxDiagonal(vector<vector<int>>& dimensions) {
        int maxDiagonalSq = -1;
        int maxArea = -1;

        for (auto &rect : dimensions) {
            int l = rect[0], w = rect[1];
            int diagSq = l * l + w * w;
            int area = l * w;

            if (diagSq > maxDiagonalSq) {
                maxDiagonalSq = diagSq;
                maxArea = area;
            } else if (diagSq == maxDiagonalSq) {
                maxArea = max(maxArea, area);
            }
        }
        return maxArea;
    }
};
