import java.io.*;
import java.util.*;

public class Main {

    static final int MIN = -100005;

    static final int[] DX = {1, 0, 0};
    static final int[] DY = {0, -1, 1};

    int n, m;
    int[][] map;
    boolean[][] vis;
    int[][][] dp;

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
            String[] n_m = br.readLine().split(" ");
            n = Integer.parseInt(n_m[0]);
            m = Integer.parseInt(n_m[1]);
            map = new int[n][m];
            vis = new boolean[n][m];
            dp = new int[n][m][3];
            for(int i=0; i<n; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int j=0; j<m; j++) {
                    Arrays.fill(dp[i][j], MIN);
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        vis[0][0] = true;
        System.out.println(Math.max(dfs(0,0,0), dfs(0,0,1)));
    }

    public int dfs(int x, int y, int d) {
        // System.out.println("x, y : " + x + ", " + y);
        if(x == n-1 && y == m-1) {
            return map[x][y];
        }

        if(dp[x][y][d] > MIN) {
            return dp[x][y][d];
        }

        int max = MIN;
        for(int dir=0; dir<3; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(vis[nx][ny]) continue;
            // System.out.println("nx, ny : " + nx + ", " + ny);
            vis[nx][ny] = true;
            max = Math.max(max, dfs(nx, ny, dir));
            // System.out.println("max : " + max);
            vis[nx][ny] = false;
        }
        // System.out.println("x, y : " + x + ", " + y);
        dp[x][y][d] = max + map[x][y];
        return dp[x][y][d];
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }

}