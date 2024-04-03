import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {0, 1, 0, -1}; //동 남 서 북
    static final int[] DY = {1, 0, -1, 0};
    static final int INF = Integer.MAX_VALUE;

    int n;
    int[][] board;
    int[][] dp;
    int idx;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        while(true) {
            input();
        }
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            idx = 1;
            while(true) {
                n = Integer.parseInt(br.readLine());
                if (n == 0) System.exit(0);
                board = new int[n][n];
                dp = new int[n][n];
                for (int i = 0; i < n; i++) {
                    String[] tmp = br.readLine().split(" ");
                    for (int j = 0; j < n; j++) {
                        board[i][j] = Integer.parseInt(tmp[j]);
                        dp[i][j] = INF;
                    }
                }
                solve();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        bfs();
    }

    public void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        dp[0][0] = board[0][0];

        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(dp[cur.x][cur.y] + board[nx][ny] < dp[nx][ny]) {
                    q.add(new Point(nx, ny));
                    dp[nx][ny] = dp[cur.x][cur.y] + board[nx][ny];
                }
            }
        }

        System.out.println("Problem " + (idx++) + ": " + dp[n-1][n-1]);
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
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