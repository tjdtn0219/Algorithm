import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int n, m;
    char[][] board;
    HashSet<Character> set;
    boolean[][] vis;

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
            board = new char[n][m];
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                for(int j=0; j<m; j++) {
                    board[i][j] = str.charAt(j);
                }
            }
            set = new HashSet<>();
            vis = new boolean[n][m];
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        vis[0][0] = true;
        set.add(board[0][0]);
        int ans = dfs(0, 0);
        System.out.println(ans);
    }

    public int dfs(int x, int y) {
        int cnt = 0;
        boolean isCanMove = false;

        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(vis[nx][ny] || set.contains(board[nx][ny])) continue;
            isCanMove = true;
            vis[nx][ny] = true;
            set.add(board[nx][ny]);
            cnt = Math.max(cnt, dfs(nx, ny) + 1);
//            System.out.println("cnt : " + cnt + " nx, ny : " + nx + " , " + ny);
            vis[nx][ny] = false;
            set.remove(board[nx][ny]);
        }

        if(!isCanMove) {
            return 1;
        }

        return cnt;

    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
}