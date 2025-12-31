// PROBLEM : (1970) Last Day Where You Can Still Cross

// SOLUTION :


class Solution {

    private int[] rank;
    private int[] root;
    private int row;
    private int col;
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    private int leftWaterId;
    private int rightWaterId;

    public int latestDayToCross(int row, int col, int[][] cells) { //DSU on water cells solution 
        this.row = row;
        this.col = col;
        rank = new int[(row * col) + 2]; // +1 is left side root which every water cell on left side will be connected to, +2 is right side 
        root = new int[(row * col) + 2]; // +1 is left side root which every water cell on left side will be connected to, +2 is right side
        leftWaterId = (row * col); // read above
        rightWaterId = (row * col) + 1; // read above
        root[leftWaterId] = leftWaterId; // read above
        root[rightWaterId] = rightWaterId; // read above
        
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                root[toId(i, j)] = toId(i, j);
            }
        }
        boolean[][] water = new boolean[row][col];

        for(int i=0; i<cells.length; i++) {
            int x = cells[i][0]-1;
            int y = cells[i][1]-1;

            water[x][y] = true;

            if(y == 0) {
                union(toId(x, y), leftWaterId);
            }
            else if(y == col-1) {
                union(toId(x, y), rightWaterId);
            }
            
            for(int[] dir: DIRECTIONS) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                if(valid(newx, newy) && water[newx][newy]) {
                    union(toId(x, y), toId(newx, newy));
                }
            }
            if(find(leftWaterId) == find(rightWaterId)) {
                return i;
            }
        }

        return -1;
    }

    private int toId(int x, int y) {
        return x * col + y;
    }

    private void union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);

        if(rank[root1] == rank[root2]) {
            rank[root1]++;
            root[root2] = root[root1];
        }
        else if(rank[root1] > rank[root2]) {
            root[root2] = root[root1];
        }
        else {
            root[root1] = root[root2];
        }
    }

    private int find(int id) {
        while(root[id] != id) {
            root[id] = root[root[id]];
            id = root[id];
        }
        return id;
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

}
