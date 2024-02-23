import java.util.*;

class Solution {
    
    static final int[] dx = {1,0,-1,0};
    static final int[] dy = {0,1,0,-1};
    
    int n, m;
    int[][] map;
    boolean[][] vis;
    List<Integer> answer;
    
    public int[] solution(String[] maps) {
        init(maps);
        solve();
        if(answer.size() == 0) answer.add(-1);
        else Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void init(String[] maps) {
        answer = new ArrayList<>();
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        vis = new boolean[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maps[i].charAt(j) == 'X') map[i][j] = -1;
                else map[i][j] = maps[i].charAt(j) - '0';
            }
        }
    }
    
    public void solve() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(vis[i][j] || map[i][j]<0) continue;
                answer.add(bfs(i, j));
            }
        }
    }
    
    public int bfs(int x, int y) {
        int sum = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        vis[x][y] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point polled = q.poll();
                sum += map[polled.x][polled.y];
                for(int dir=0; dir<4; dir++) {
                    int nx = polled.x + dx[dir];
                    int ny = polled.y + dy[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny] || map[nx][ny] == -1) continue;
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
        }
        return sum;
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