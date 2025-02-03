import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static final int MAX = 100;
    static final int[] DX = {0,1,0,-1};
    static final int[] DY = {1,0,-1,0};
    
    int sx, sy;
    int ex, ey;
    int[][] rectangle;
    int[][] map;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        init(rectangle, characterX, characterY, itemX, itemY);
        return solve();
    }

    public int solve() {
        // printMap();
        Queue<Point> q = new LinkedList<>();
        boolean[][] vis = new boolean[MAX+5][MAX+5];
        q.add(new Point(sx, sy));
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                if(cur.x==ex && cur.y==ey) return cnt/2;
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(map[nx][ny]==1 && !vis[nx][ny]) {
                        q.add(new Point(nx, ny));
                        vis[nx][ny] = true;
                    }
                }    
            }
            cnt++;
        }
        return -1;
    }
    
    public void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=MAX; i++) {
            for(int j=0; j<=MAX; j++) {
                if(map[i][j] > 0) {
                    sb.append(map[i][j]);    
                } else {
                    sb.append(" ");   
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void init(int[][] rectangle, int sx, int sy, int ex, int ey) {
        this.rectangle = rectangle;
        this.sx = 2*sx;
        this.sy = 2*sy;
        this.ex = 2*ex;
        this.ey = 2*ey;
        this.map = new int[MAX+5][MAX+5];
        for(int[] pos : rectangle) {
            int x1 = pos[0];
            int y1 = pos[1];
            int x2 = pos[2];
            int y2 = pos[3];
            for(int i=2*x1; i<=2*x2; i++) {
                for(int j=2*y1; j<=2*y2; j++) {
                    if(map[i][j] == 2) continue;
                    map[i][j] = 2;                    
                    if(i==2*x1 || i==2*x2 || j==2*y2 || j==2*y1) map[i][j] = 1;
                }
            }
        }
    }
    
}