import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int n, m;
    int[][] map;
    int[][] dp;
    int answer;

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
            map = new int[n][m];
            dp = new int[n][m];
            for(int i=0; i<n; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(dp[i], -1);
            }
            answer = 0;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
//        printMap();
        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    public void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public int dfs(int x, int y) {
        if(x == n-1 && y == m-1) {
            return 1;
        }

        if(dp[x][y] > -1) {
            return dp[x][y];
        }

        int cnt = 0;
        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(map[x][y] <= map[nx][ny]) continue;
            cnt += dfs(nx, ny);
        }

        dp[x][y] = cnt;
        return dp[x][y];
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }

}
