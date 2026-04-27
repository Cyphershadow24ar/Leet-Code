// PROBLEM : (1391) Check if There is a Valid Path in a Grid

// SOLUTION :

class Solution {
    public boolean hasValidPath(int[][] g) {
        int m = g.length, n = g[0].length;
        
        boolean[][] vis = new boolean[m][n];
        
        // directions: up, down, left, right
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        
        // allowed directions for each type
        int[][][] allow = new int[7][][];
        
        allow[1] = new int[][]{{0,-1},{0,1}};
        allow[2] = new int[][]{{-1,0},{1,0}};
        allow[3] = new int[][]{{0,-1},{1,0}};
        allow[4] = new int[][]{{0,1},{1,0}};
        allow[5] = new int[][]{{0,-1},{-1,0}};
        allow[6] = new int[][]{{0,1},{-1,0}};
        
        java.util.Queue<int[]> q = new java.util.LinkedList<>();
        q.add(new int[]{0,0});
        vis[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            
            if (r == m-1 && c == n-1) return true;
            
            int type = g[r][c];
            
            for (int i = 0; i < allow[type].length; i++) {
                int nr = r + allow[type][i][0];
                int nc = c + allow[type][i][1];
                
                if (nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
                if (vis[nr][nc]) continue;
                
                int nextType = g[nr][nc];
                
                // check reverse connection
                for (int j = 0; j < allow[nextType].length; j++) {
                    int br = nr + allow[nextType][j][0];
                    int bc = nc + allow[nextType][j][1];
                    
                    if (br == r && bc == c) {
                        vis[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }
        
        return false;
    }
}
