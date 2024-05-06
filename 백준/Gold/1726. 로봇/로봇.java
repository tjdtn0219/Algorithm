import java.io.*;

public class Main {

    static final int[] DX = {-1, 0, 0, 1, -1};  //동 서 남 북
    static final int[] DY = {-1, 1, -1, 0, 0};

    int n, m;
    int[][] map;
    Point startPoint;
    Point endPoint;
    int[][][] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        initDP();
        solve();
//        System.out.println("startPoint : " + startPoint.x + ", " + startPoint.y + ", " + startPoint.dir);
//        System.out.println("endPoint : " + endPoint.x + ", " + endPoint.y + ", " + endPoint.dir);
//        printDP();
    }

    public void printDP() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(i + ", " + j + " : ");
                for(int k=1; k<=4; k++) {
                    System.out.print(dp[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            map = new int[n][m];
            for(int i=0; i<n; i++) {
                tmp = br.readLine().split(" ");
                for(int j=0; j<m; j++) {
                    map[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            int dir = Integer.parseInt(tmp[2]);
            startPoint = new Point(x-1, y-1, dir);
            tmp = br.readLine().split(" ");
            x = Integer.parseInt(tmp[0]);
            y = Integer.parseInt(tmp[1]);
            dir = Integer.parseInt(tmp[2]);
            endPoint = new Point(x-1, y-1, dir);
            dp = new int[n][m][5];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void initDP() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int k=0; k<5; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dp[startPoint.x][startPoint.y][startPoint.dir] = 0;
    }

    public void solve() {
        dfs(startPoint.x, startPoint.y, startPoint.dir, 0);
        System.out.println(dp[endPoint.x][endPoint.y][endPoint.dir]);
    }

    public void dfs(int x, int y, int curDir, int cnt) {
        if(x == endPoint.x && y == endPoint.y && curDir == endPoint.dir) {
            return ;
        }

        for(int dir=1; dir<=4; dir++) {
            int turnCnt = getTurnCnt(curDir, dir);
            if(cnt + turnCnt < dp[x][y][dir]) {
                dp[x][y][dir] = cnt + turnCnt;
                dfs(x, y, dir, cnt + turnCnt);
            }
        }
        for(int dist=1; dist<=3; dist++) {
            int nx = x + dist * DX[curDir];
            int ny = y + dist * DY[curDir];
            if(!isInner(nx, ny)) break;
            if(map[nx][ny] == 1) break;
            if(cnt + 1 < dp[nx][ny][curDir]) {
                dp[nx][ny][curDir] = cnt + 1;
                dfs(nx, ny, curDir, cnt + 1);
            }
        }
    }

    public int getTurnCnt(int curDir, int dir) {
        // 1 2 3 4
        // 동 서 남 북
        if(curDir == dir) return 0;
        if(curDir == 1) {
            if(dir == 2) return 2;
            else return 1;
        } else if(curDir == 2) {
            if(dir == 1) return 2;
            else return 1;
        } else if(curDir == 3) {
            if(dir == 4) return 2;
            else return 1;
        } else {
            if(dir == 3) return 2;
            else return 1;
        }
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }

}

class Node {
    Point point;
    int dir;
    public Node(Point point, int dir) {
        this.point = point;
        this.dir = dir;
    }
}

class Point {
    int x;
    int y;
    int dir;
    public Point(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}