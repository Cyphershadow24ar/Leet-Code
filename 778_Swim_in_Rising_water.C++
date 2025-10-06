// PROBLEM : (778) Swim in Rising Water

// SOLUTION : 
class Solution {
public:
    int swimInWater(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<int>> visited(n, vector<int>(n,0));
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

        pq.push({grid[0][0], 0, 0});
        visited[0][0] =1;

        int directions[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};

        while(!pq.empty()){
            auto top = pq.top(); pq.pop();
            int t = top[0], i=top[1], j =top[2];

            if(i == n-1 && j == n-1) return t;

            for(auto &dir :  directions){
                int x = i + dir[0];
                int y = j + dir[1];

                if(x >= 0 && y >= 0 && x < n && y < n && !visited[x][y]){
                    visited[x][y] = 1;
                    pq.push({max(t, grid[x][y]), x, y});
                }
            }
        }
        return -1;
    }
};
