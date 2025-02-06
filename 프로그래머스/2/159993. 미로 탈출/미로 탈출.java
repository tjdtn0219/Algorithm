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
    static final int[] DY = {0,-1,0,1};
    
    int n, m;
    char[][] map;
    int ans;
    Point st;
    Point en;
    Point lever;
    
    public int solution(String[] maps) {
        init(maps);
        solve();
        return ans;
    }
    
    public void solve() {
        System.out.println("st : " + st.x + ", " + st.y);
        System.out.println("en : " + en.x + ", " + en.y);
        System.out.println("lever : " + lever.x + ", " + lever.y);
        int dis1 = bfs(st, lever);
        int dis2 = bfs(lever, en);
        System.out.println("dis1 : " + dis1 + ", dis2 : " + dis2);
        if(dis1 == -1 || dis2 == -1) {
            ans = -1;
        } else {
            ans += dis1 + dis2;
        }
    }
    
    public boolean isInner(int x,int y) {
        return 0<=x && x<n && 0<=y && y<m;
    }
    
    public int bfs(Point st, Point en) {
        // System.out.println("bfs st : " + st.x + ", " + st.y);
        // System.out.println("bfs en : " + en.x + ", " + en.y);
        Queue<Point> q = new LinkedList<>();
        q.add(st);
        boolean[][] vis = new boolean[n][m];
        vis[st.x][st.y] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                if(cur.x == en.x && cur.y == en.y) return cnt;
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny] || map[nx][ny] == 'X') continue;
                    // System.out.println(cur.x + ", " + cur.y + " --> " + nx + ", " + ny);
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
            cnt++;
        }
        return -1;
    }
    
    public void init(String[] maps) {
        this.n = maps.length;
        this.m = maps[0].length();
        this.map = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') {
                    st = new Point(i, j);
                }
                if(map[i][j] == 'E') {
                    en = new Point(i, j);
                }
                if(map[i][j] == 'L') {
                    lever = new Point(i, j);
                }
            }
        }
        
    }
}