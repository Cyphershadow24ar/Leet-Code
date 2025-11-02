// PROBLEM : (2257) Count Unguarded Cells in the Gird

// SOLUTION :

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int countUnguarded(int m, int n, vector<vector<int>>& guards, vector<vector<int>>& walls) {
        vector<vector<int>> grid(m, vector<int>(n, 0));
        
        // Mark guards and walls
        for (auto &g : guards) grid[g[0]][g[1]] = 2; // Guard
        for (auto &w : walls) grid[w[0]][w[1]] = 1; // Wall

        // Directions: up, down, left, right
        vector<pair<int,int>> dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        
        // Guard visibility
        for (auto &g : guards) {
            int r = g[0], c = g[1];
            for (auto &d : dirs) {
                int x = r + d.first, y = c + d.second;
                while (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 1 && grid[x][y] != 2) {
                    if (grid[x][y] == 0) grid[x][y] = 3; // Mark as guarded
                    x += d.first;
                    y += d.second;
                }
            }
        }

        // Count unguarded cells
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) count++;
            }
        }
        return count;
    }
};
