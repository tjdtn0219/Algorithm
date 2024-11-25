import java.util.*;

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    
    int n, m;
    int[][] map;
    Point rp;
    Point bp;
    int ans;
    boolean[][] visR;
    boolean[][] visB;
    
    public int solution(int[][] maze) {
        init(maze);
        solve();
        return ans;
    }
    
    public void solve() {
        visR[rp.x][rp.y] = true;
        visB[bp.x][bp.y] = true;
        dfs(0, rp.x, rp.y, bp.x, bp.y);
        System.out.println(ans);
        if(ans == Integer.MAX_VALUE) ans = 0;
    }
    
    public void dfs(int k, int rx, int ry, int bx, int by) {
        // System.out.println("k : " + k + ", " + rx + "," + ry + "," + bx + "," + by);
        if(map[rx][ry] == 3 && map[bx][by] == 4) {
            ans = Math.min(ans, k);
            return ;
        }
        
        if(map[rx][ry] == 3) {
            for(int dir=0; dir<4; dir++) {
                int nbx = bx + DX[dir];
                int nby = by + DY[dir];
                // System.out.println("nbx, nby : " +nbx + "," + nby);
                if(!isInner(nbx, nby)) continue;
                if(visB[nbx][nby] || (nbx==rx && nby==ry) || map[nbx][nby]==5) continue;
                // System.out.println("TAG : nbx, nby : " +nbx + "," + nby);
                visB[nbx][nby] = true; 
                dfs(k+1, rx, ry, nbx, nby);
                visB[nbx][nby] = false;
            }
            return ;
        }
        
        if(map[bx][by] == 4) {
            for(int dir=0; dir<4; dir++) {
                int nrx = rx + DX[dir];
                int nry = ry + DY[dir];
                if(!isInner(nrx, nry)) continue;
                if(visR[nrx][nry] || (nrx==bx && nry==by) || map[nrx][nry]==5) continue;
                visR[nrx][nry] = true;
                dfs(k+1, nrx, nry, bx, by);
                visR[nrx][nry] = false;
            }
            return ;
        }
        
        for(int d1=0; d1<4; d1++) {
            for(int d2=0; d2<4; d2++) {
                int nrx = rx + DX[d1];
                int nry = ry + DY[d1];
                int nbx = bx + DX[d2];
                int nby = by + DY[d2];
                if(!isInner(nrx, nry) || !isInner(nbx, nby)) continue;
                if(isSame(nrx, nry, nbx, nby)) continue;
                if(visR[nrx][nry] || visB[nbx][nby]) continue;
                if(map[nrx][nry]==5 || map[nbx][nby]==5) continue;
                if(nrx==bx && nry==by && nbx==rx && nby==ry) continue;
                visR[nrx][nry] = true;
                visB[nbx][nby] = true;
                dfs(k+1, nrx, nry, nbx, nby);
                visR[nrx][nry] = false;
                visB[nbx][nby] = false;
            }
        }
    }
    
    public boolean isSame(int x1, int y1, int x2, int y2) {
        if(x1==x2 && y1==y2) return true;
        return false;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
    
    public void init(int[][] maze) {
        ans = Integer.MAX_VALUE;
        this.map = maze;
        n = maze.length;
        m = maze[0].length;
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maze[i][j] == 1) {
                    rp = new Point(i, j);
                } else if(maze[i][j] == 2) {
                    bp = new Point(i, j);
                }
            }
        }
        visR = new boolean[n][m];
        visB = new boolean[n][m];
    }
    
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}