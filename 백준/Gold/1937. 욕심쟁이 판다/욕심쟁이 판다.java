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
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            dp = new int[n][n];
            for(int i=0; i<n; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(dp[i], -1);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        // System.out.println("test : " + dfs(0, 1));
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(dp[i][j] == -1) {
                    dfs(i, j);
                }
            }
        }
        // dfs(0, 0);
        // dfs(0, 1);
        // dfs(0, 2);
        // dfs(0, 3);

        // printArr(dp);
        System.out.println(getMaxVal(dp));
    }

    private int getMaxVal(int[][] dp) {
        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    private void printArr(int[][] dp) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public int dfs(int x, int y) {

        if(dp[x][y] > 0) return dp[x][y];

        int max = 0;
        boolean flag = false;
        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(map[nx][ny] > map[x][y]) {
                flag = true;
                max = Math.max(max, dfs(nx, ny) + 1);
            }
        }

        if(!flag) {
            dp[x][y] = 1;
            return 1;
        } else {
            dp[x][y] = max;
            return max;
        }

    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }


}