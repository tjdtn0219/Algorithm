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
    
    char[][] map;
    int n, m;
    String[] requests;
    int ans;
    
    public int solution(String[] storage, String[] requests) {
        init(storage, requests);
        solve();
        return ans;
    }
    
    public void solve() {
        // System.out.println("n , m : " + n + ", " + m);
        // printMap();
        
        // - : 외부 벽
        // * : 외부 벽인지 판별 전
        for(String request : requests) {
            if(request.length() == 1) {
                // 지게차
                System.out.println("지게차 : " + request.charAt(0));
                lift(request.charAt(0));
            } else {
                // 크레인
                System.out.println("크레인 : " + request.charAt(0));
                crain(request.charAt(0));
            }
            initOut();
            // System.out.println("======AFTER======");
            // printMap();
        }
        // System.out.println("======AFTER======");
        // printMap();
        ans = getCnt();
    }
    
    public void initOut() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] vis = new boolean[n+2][m+2];
        q.add(new Point(0, 0));
        vis[0][0] = true;
        
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                map[cur.x][cur.y] = '-';
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny] || ('A' <= map[nx][ny] && map[nx][ny] <= 'Z')) continue;
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
        }
    }
    
    public void lift(char c) {
        List<Point> removeList = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                boolean flag = false;
                if(map[i][j] == c) {
                    if(isTouchOut(i, j)) {
                        // map[i][j] = '-';
                        removeList.add(new Point(i, j));
                    }
                }
            }
        }
        for(Point p : removeList) {
            map[p.x][p.y] = '*';
        }
    }
    
    public void crain(char c) {
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(map[i][j] == c) {
                    map[i][j] = '*';
                }
            }
        }
    }
    
    private int getCnt() {
        int cnt = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if('A' <= map[i][j] && map[i][j] <= 'Z') {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    public boolean isTouchOut(int x, int y) {
        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(map[nx][ny] == '-') return true;
        }
        return false;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n+2 && y<m+2;
    }
    
    private void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n+2; i++) {
            for(int j=0; j<m+2; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void init(String[] storage, String[] requests) {
        this.n = storage.length;
        this.m = storage[0].length();
        this.map = new char[n+2][m+2];
        this.requests = requests;
        for(int i=0; i<n+2; i++) {
            Arrays.fill(map[i], '-');
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i+1][j+1] = storage[i].charAt(j);
            }
        }
        // n += 2;
        // m += 2;
    }
    
    
}