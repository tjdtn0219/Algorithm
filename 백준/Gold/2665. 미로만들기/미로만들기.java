import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    int n;
    int[][] map;
    int[][] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        bfs();
        int answer = dp[n-1][n-1];
        System.out.println(answer);
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            dp = new int[n][n];
            for(int i=0; i<n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        dp[0][0] = 0;
        while(!q.isEmpty()) {
            Point polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(!isInner(nx, ny)) continue;
                if(map[nx][ny] == 0) {
                    if(dp[x][y]+1 < dp[nx][ny]) {
                        dp[nx][ny] = dp[x][y] + 1;
                        q.add(new Point(nx, ny));
                    }
                } else {
                    if(dp[x][y] < dp[nx][ny]) {
                        dp[nx][ny] = dp[x][y];
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }
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