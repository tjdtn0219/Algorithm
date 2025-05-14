import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static final int N = 10;
    static final int[] DX = {-1,1,0,0};
    static final int[] DY = {0,0,-1,1};
    
    HashMap<Character, Integer> dirMap;
    boolean[][][] vis;
    int answer;
    String dirs;
    
    public int solution(String dirs) {
        init(dirs);
        solve();
        return answer;
    }
    
    public void solve() {
        Point cur = new Point(5, 5);
        int cnt = 0;
        for(char c : dirs.toCharArray()) {
            int dir = dirMap.get(c);
            int nx = cur.x + DX[dir];
            int ny = cur.y + DY[dir];
            if(!isInner(nx, ny)) continue;
                        
            if(!isVis(cur.x, cur.y, nx, ny, dir)) {
                vis[nx][ny][dir] = true;
                int rDir = getReverse(dir);
                vis[cur.x][cur.y][rDir] = true;
                cnt++;
            }

            cur.x = nx;
            cur.y = ny;
        }
        answer = cnt;
    }
    
    public int getReverse(int dir) {
        if(dir == 0) return 1;
        if(dir == 1) return 0;
        if(dir == 2) return 3;
        if(dir == 3) return 2;
        return -1;
    }
    
    public boolean isVis(int x, int y, int nx, int ny, int dir) {
        int rDir = getReverse(dir);
        return vis[nx][ny][dir] | vis[x][y][rDir];
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<=N && y<=N;
    }
    
    public void init(String dirs) {
        this.dirs = dirs;
        this.vis = new boolean[N+1][N+1][4];
        this.dirMap = new HashMap<>();
        dirMap.put('U', 0);
        dirMap.put('D', 1);
        dirMap.put('L', 2);
        dirMap.put('R', 3);
    }
}