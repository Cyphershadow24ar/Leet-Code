// PORBLEM : (417) Pacific Atlantic Water Flow

// SOLUTION :

#include <vector>
#include <queue>
#include <utility> // For std::pair

class Solution {
private:
    int m, n;
    std::vector<std::vector<int>> grid_heights; // Store heights grid as a member for easier access in BFS
    
    // Directions for moving (up, down, left, right)
    int dr[4] = {-1, 1, 0, 0};
    int dc[4] = {0, 0, -1, 1};

    // Helper BFS function to find all cells reachable from a given set of starting points
    void bfs(std::queue<std::pair<int, int>>& q, std::vector<std::vector<bool>>& reachable) {
        while (!q.empty()) {
            std::pair<int, int> curr = q.front();
            q.pop();
            int r = curr.first;
            int c = curr.second;

            // Explore all 4 neighbors
            for (int i = 0; i < 4; ++i) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // Check bounds
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                // Check if already visited/reachable from this ocean
                if (reachable[nr][nc]) {
                    continue;
                }
                // Check the water flow condition (reversed: water can "flow uphill" from current to neighbor)
                // This means, if neighbor's height is greater than or equal to current cell's height,
                // then water from the neighbor can eventually reach the current cell, and thus the ocean.
                if (grid_heights[nr][nc] >= grid_heights[r][c]) {
                    reachable[nr][nc] = true; // Mark as reachable
                    q.push({nr, nc});         // Add to queue for further exploration
                }
            }
        }
    }

public:
    std::vector<std::vector<int>> pacificAtlantic(std::vector<std::vector<int>>& heights) {
        if (heights.empty() || heights[0].empty()) {
            return {};
        }

        grid_heights = heights; // Copy the heights grid
        m = heights.size();
        n = heights[0].size();

        // Boolean matrices to mark cells reachable from Pacific and Atlantic
        std::vector<std::vector<bool>> pacificReachable(m, std::vector<bool>(n, false));
        std::vector<std::vector<bool>> atlanticReachable(m, std::vector<bool>(n, false));

        // Queues for BFS traversals
        std::queue<std::pair<int, int>> qPacific;
        std::queue<std::pair<int, int>> qAtlantic;

        // Initialize BFS for Pacific Ocean: cells on top row and left column
        for (int i = 0; i < m; ++i) {
            // Leftmost column cells
            if (!pacificReachable[i][0]) { // Prevent duplicate pushes if (0,0) is handled twice
                qPacific.push({i, 0});
                pacificReachable[i][0] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            // Topmost row cells
            if (!pacificReachable[0][j]) { // Prevent duplicate pushes if (0,0) is handled twice
                qPacific.push({0, j});
                pacificReachable[0][j] = true;
            }
        }

        // Initialize BFS for Atlantic Ocean: cells on bottom row and right column
        for (int i = 0; i < m; ++i) {
            // Rightmost column cells
            if (!atlanticReachable[i][n - 1]) {
                qAtlantic.push({i, n - 1});
                atlanticReachable[i][n - 1] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            // Bottommost row cells
            if (!atlanticReachable[m - 1][j]) {
                qAtlantic.push({m - 1, j});
                atlanticReachable[m - 1][j] = true;
            }
        }

        // Run BFS for both oceans
        bfs(qPacific, pacificReachable);
        bfs(qAtlantic, atlanticReachable);

        // Collect cells that can reach both oceans
        std::vector<std::vector<int>> result;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.push_back({i, j});
                }
            }
        }

        return result;
    }
};
