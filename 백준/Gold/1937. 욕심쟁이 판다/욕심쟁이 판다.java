import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int n;
    int[][] map;
    int[][] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
//        init();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            map = new int[n][n];
            for(int i=0; i<n; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            dp = new int[n][n];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void init() {
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    public void solve() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(dp[i][j] > 0) continue;
                dfs(i, j);
            }
        }
//        dfs(3, 1);
//        printArr(dp);
        int ans = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                ans = Math.max(ans, dp[i][j]);
            }
        }
        System.out.println(ans);
    }

    public void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public int dfs(int x, int y) {
        if(dp[x][y] > 0) return dp[x][y];

        boolean flag = false;
        int max = 0;

        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(map[x][y] < map[nx][ny]) {
                flag = true;
//                System.out.println("x, y : " + x + ", " + y + ", nx, ny : " + nx + ", " + ny);
                max = Math.max(max, dfs(nx, ny) + 1);
            }
        }

        if(!flag) {
            dp[x][y] = 1;
            return 1;
        }

        dp[x][y] = max;
        return max;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }
}
