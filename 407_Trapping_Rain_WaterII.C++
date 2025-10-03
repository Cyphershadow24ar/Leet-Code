// PROBLEM : (407) Trapping Rain Water II 

// SOLUTION :

#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    int trapRainWater(vector<vector<int>>& heightMap) {
        int m = heightMap.size();
        if (m == 0) return 0;
        int n = heightMap[0].size();
        if (n == 0) return 0;

        vector<vector<bool>> visited(m, vector<bool>(n, false));

        // Min-heap: (height, row, col)
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

        // Push all boundary cells into heap
        for (int i = 0; i < m; i++) {
            pq.push({heightMap[i][0], i, 0});
            pq.push({heightMap[i][n-1], i, n-1});
            visited[i][0] = visited[i][n-1] = true;
        }
        for (int j = 1; j < n-1; j++) {
            pq.push({heightMap[0][j], 0, j});
            pq.push({heightMap[m-1][j], m-1, j});
            visited[0][j] = visited[m-1][j] = true;
        }

        int water = 0;
        vector<int> dirs = {0, 1, 0, -1, 0};

        while (!pq.empty()) {
            auto cell = pq.top();
            pq.pop();
            int height = cell[0], r = cell[1], c = cell[2];

            // Check 4-directional neighbors
            for (int d = 0; d < 4; d++) {
                int nr = r + dirs[d], nc = c + dirs[d+1];
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc]) 
                    continue;

                visited[nr][nc] = true;
                // If neighbor is lower, trap water
                water += max(0, height - heightMap[nr][nc]);
                // Push the max of current boundary and neighbor
                pq.push({max(height, heightMap[nr][nc]), nr, nc});
            }
        }

        return water;
    }
};
