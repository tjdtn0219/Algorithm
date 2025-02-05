import java.util.*;

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,-1,0,1};
    
    int n, m;
    int[][] map;
    boolean[][] vis;
    int ans;
    
    public int[] solution(String[] maps) {
        // int[] answer = {};
        init(maps);
        return solve();
        // return answer;
    }
    
    public void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public int[] solve() {
        // printMap();
        List<Integer> ansList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(vis[i][j] || map[i][j] == 0) continue;
                // System.out.println("x, y : " + i + ", " + j);
                int cnt = bfs(i, j);
                ansList.add(cnt);
            }
        }
        if(ansList.isEmpty()) {
            ansList.add(-1);
        }
        Collections.sort(ansList);
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        vis[x][y] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            // System.out.println("bfs : x, y : " + cur.x + ", " + cur.y);
            cnt += map[cur.x][cur.y];
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis[nx][ny] || map[nx][ny] == 0) continue;
                q.add(new Point(nx, ny));
                vis[nx][ny] = true;
            }
        }
        
        return cnt;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && x<n && 0<=y && y<m;
    }
    
    public void init(String[] maps) {
        this.n = maps.length;
        this.m = maps[0].length();
        this.map = new int[n][m];
        this.vis = new boolean[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maps[i].charAt(j) == 'X') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = maps[i].charAt(j) - '0';
                }
            }
        }
    }
}