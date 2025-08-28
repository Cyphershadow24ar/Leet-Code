// PROBLEM : (3446) Sort Matrix by Diagonals

// SOLUTION :

class Solution {
public:
    vector<vector<int>> sortMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        
        // Map from diagonal key (i-j) to vector of elements
        unordered_map<int, vector<int>> diagMap;

        // Collect elements from each diagonal
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diagMap[i - j].push_back(grid[i][j]);
            }
        }

        // Sort diagonals based on their position
        for (auto &it : diagMap) {
            int key = it.first;
            if (key >= 0) {
                // bottom-left (including main diag) → non-increasing
                sort(it.second.begin(), it.second.end(), greater<int>());
            } else {
                // top-right → non-decreasing
                sort(it.second.begin(), it.second.end());
            }
        }

        // Put elements back into grid
        unordered_map<int, int> idx;  // track index used in each diagonal
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                grid[i][j] = diagMap[key][idx[key]++];
            }
        }

        return grid;
    }
};
