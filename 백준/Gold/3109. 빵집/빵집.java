import java.io.*;

public class Main{

    static final int[] DX = {-1, 0, 1};
    static final int[] DY = {1, 1, 1};

    int n, m;
    char[][] map;
    boolean[][] isPiped;
    int ans;

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
            String[] N_M = br.readLine().split(" ");
            n = Integer.parseInt(N_M[0]);
            m = Integer.parseInt(N_M[1]);
            isPiped = new boolean[n][m];
            map = new char[n][m];
            for(int i=0; i<n; i++) {
                String s = br.readLine();
                for(int j=0; j<m; j++) {
                    map[i][j] = s.charAt(j);
                }
            }
            ans = 0;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int i=0; i<n; i++) {
            if(dfs(i, 0)) ans++;
        }
        System.out.println(ans);

    }

    public boolean dfs(int x, int y) {
		map[x][y] = '-';

        if(y == m-1) {
            return true;
        }
		
        for(int dir=0; dir<3; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(map[nx][ny] != '.') continue;
            boolean flag = dfs(nx, ny);
            if(flag) return true;
        }
        return false;
	}

    private boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }

}
