// PROBLEM : (1559) Detect Cycles in 2D Grid

// SOLUTION :

class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If not visited, start a new DFS search
                if (!visited[i][j]) {
                    if (dfs(grid, visited, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visited, int r, int c, int pr, int pc, char target) {
        visited[r][c] = true;

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // Check boundaries and character match
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == target) {
                // If the neighbor is visited and NOT the parent, we found a cycle!
                if (visited[nr][nc]) {
                    if (nr != pr || nc != pc) {
                        return true;
                    }
                } else {
                    // Continue DFS
                    if (dfs(grid, visited, nr, nc, r, c, target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
