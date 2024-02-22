import java.util.*;

class Solution {
    
    static final int[] dx = {1,0,-1,0};
    static final int[] dy = {0,1,0,-1};
    
    int n, m;
    char[][] map;
    Point start;
    Point lever;
    Point end;
    
    public int solution(String[] maps) {
        int answer = 0;
        init(maps);
        int startToLever = bfs(start, lever);
        if(startToLever == -1) return -1;
        int leverToEnd = bfs(lever, end);
        if(leverToEnd == -1) return -1;
        answer = startToLever + leverToEnd;
        return answer;
    }
    
    public void init(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') start = new Point(i, j);
                if(map[i][j] == 'L') lever = new Point(i, j);
                if(map[i][j] == 'E') end =new Point(i, j);
            }
        }
    }
    
    public int bfs(Point sp, Point ep) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        q.add(sp);
        vis[sp.x][sp.y] = true;
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                if(cur.x == ep.x && cur.y == ep.y) return cnt;
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny] || map[nx][ny] == 'X') continue;
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
            cnt++;
        }
        return -1;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}