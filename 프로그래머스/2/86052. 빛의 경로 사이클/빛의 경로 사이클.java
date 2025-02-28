import java.util.*;


class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static final int[] DX = {1,0,-1,0}; //남 동 북 서
    static final int[] DY = {0,1,0,-1};
    
    int n, m;
    char[][] grid;
    boolean[][][] vis;
    
    public int[] solution(String[] grid) {
        init(grid);
        return solve();
    }
    
    public int[] solve() {
        List<Integer> ansList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int d=0; d<4; d++) {
                    if(vis[i][j][d]) continue;
                    ansList.add(startLight(i, j, d));
                }
            }
        }
        Collections.sort(ansList);
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int startLight(int x, int y, int dir) {
        int cnt = 0;
        
        while(true) {
            if(vis[x][y][dir]) break;
            vis[x][y][dir] = true;
            if(grid[x][y]=='L') dir = (dir+1) % 4;
            else if(grid[x][y]=='R') dir= (dir+3) % 4;
            int nx = getRotateX(x + DX[dir]);
            int ny = getRotateY(y + DY[dir]);
            
            x = nx;
            y = ny;
            cnt++;
        }
        return cnt;
    }
    
    public int getRotateX(int x) {
        if(x < 0) return n-1;
        else if(x >= n) return 0;
        return x;
    }
    
    public int getRotateY(int y) {
        if(y < 0) return m-1;
        else if(y >= m) return 0;
        return y;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
    
    public void init(String[] grid) {
        this.n = grid.length;
        this.m = grid[0].length();
        this.grid = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                this.grid[i][j] = grid[i].charAt(j);
            }
        }
        this.vis = new boolean[n][m][4];
    }
    
}