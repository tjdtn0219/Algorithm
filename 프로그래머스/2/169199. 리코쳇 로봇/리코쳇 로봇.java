import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    
    int n, m;
    char[][] map;
    int answer;
    Point sp;
    Point ep;
    
    public int solution(String[] board) {
        init(board);
        solve();
        return answer;
    }
    
    public void solve() {
        answer = bfs();
    }
    
    public int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        q.add(sp);
        vis[sp.x][sp.y] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                // System.out.println("cur : " + cur.x + ", " + cur.y);
                if(cur.x == ep.x && cur.y == ep.y) return cnt;
                for(int dir=0; dir<4; dir++) {
                    Point nxt = move(cur, dir);
                    if(vis[nxt.x][nxt.y]) continue;
                    q.add(nxt);
                    vis[nxt.x][nxt.y] = true;
                }
            }
            cnt++;
        }
        
        return -1;
    }
    
    public Point move(Point cur, int dir) {
        int x = cur.x;
        int y = cur.y;
        while(true) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) break;
            if(map[nx][ny] == 'D') break;
            x = nx;
            y = ny;
        }
        return new Point(x, y);
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
    
    public void init(String[] board) {
        this.n = board.length;
        this.m = board[0].length();
        this.map = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    sp = new Point(i, j);
                    map[i][j] = '.';
                } else if(map[i][j] == 'G') {
                    ep = new Point(i, j);
                    map[i][j] = '.';
                }
            }
        }
    }
    
}