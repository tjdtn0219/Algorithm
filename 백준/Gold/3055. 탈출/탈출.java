import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    static final String FAIL = "KAKTUS";

    int n, m;
    char[][] map;
    List<Point> waterPoints;
    Point curPoint;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            map = new char[n][m];
            waterPoints = new ArrayList<>();
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                for(int j=0; j<m; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '*') waterPoints.add(new Point(i, j));
                    if(map[i][j] == 'S') {
                        curPoint = new Point(i, j);
                        map[i][j] = '.';
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int ans = bfs();
        if(ans == -1) {
            System.out.println(FAIL);
        } else {
            System.out.println(ans);
        }
    }

    public int bfs() {
        Queue<Point> sQ = new LinkedList<>();
        Queue<Point> wQ = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        vis[curPoint.x][curPoint.y] = true;
        sQ.add(curPoint);
        for(Point wPoint : waterPoints) {
            wQ.add(wPoint);
        }
        int cnt = 0;

        while(!sQ.isEmpty()) {
            int sQSize = sQ.size();
            int wQSize = wQ.size();
            for(int i=0; i<wQSize; i++) {
                Point polled = wQ.poll();
                int x = polled.x;
                int y = polled.y;
                for(int dir=0; dir<4; dir++) {
                    //water
                    int nx = x + DX[dir];
                    int ny = y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(map[nx][ny] != '.') continue;
                    map[nx][ny] = '*';
                    wQ.add(new Point(nx, ny));
                }
            }
            for(int i=0; i<sQSize; i++) {
                //고슴도치
                Point polled = sQ.poll();
                int x = polled.x;
                int y = polled.y;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + DX[dir];
                    int ny = y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny]) continue;
                    if(map[nx][ny] == 'D') return cnt+1;
                    if(map[nx][ny] != '.') continue;
                    sQ.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
            cnt++;
//            System.out.println("Cnt : " + cnt);
//            printMap();
        }
        return -1;
    }

    public void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
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