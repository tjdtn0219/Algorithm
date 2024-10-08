import java.io.*;

public class Main {

    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};
	
    int n, m;
    int[][] board;
    boolean[][] vis;
    int[][] dp;
    boolean isCycle;
    int max;

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
            board = new int[n][m];
            vis = new boolean[n][m];
            dp = new int[n][m];
            isCycle = false;
            max = 0;
            for(int i=0; i<n; i++) {
                String s = br.readLine();
                for(int j=0; j<m; j++) {
                    if(s.charAt(j) == 'H') board[i][j] = -1;
                    else board[i][j] = s.charAt(j) - '0';
                }
            }
            
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        vis[0][0] = true;
        dfs(0, 0, 1);
        if(isCycle) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    public void dfs(int x, int y, int k) {
        if(k > max) {
            max = k;
        }
        dp[x][y] = k;

        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir]*board[x][y];
            int ny = y + DY[dir]*board[x][y];
            if(!isInner(nx, ny)) continue;
            if(board[nx][ny] == -1) continue;
            if(vis[nx][ny]) {
                isCycle = true;
                return ;
            }
            if(k < dp[nx][ny]) continue;

            vis[nx][ny] = true;
            dfs(nx, ny, k+1);
            vis[nx][ny] = false;
        }
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }

}