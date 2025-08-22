// PROBLEM :- (3195) Find the Minimum Area to Cover All Ones I

class Solution {
public:
    int minimumArea(vector<vector<int>>& grid) {
        int n = grid.size(), m = grid[0].size();
        
        int minRow = n, maxRow = -1;
        int minCol = m, maxCol = -1;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    minRow = min(minRow, i);
                    maxRow = max(maxRow, i);
                    minCol = min(minCol, j);
                    maxCol = max(maxCol, j);
                }
            }
        }
        
        int height = maxRow - minRow + 1;
        int width  = maxCol - minCol + 1;
        
        return height * width;
    }
};
