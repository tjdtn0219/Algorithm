import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int n, m;
    char[][] board;
    List<Point> coins;
    boolean[][] vis1;
    boolean[][] vis2;
    HashSet<String> set;
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
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            board = new char[n][m];
            coins = new ArrayList<>();
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                for(int j=0; j<m; j++) {
                    board[i][j] = str.charAt(j);
                    if(board[i][j] == 'o') {
                        coins.add(new Point(i, j));
                        board[i][j] = '.';
                    }
                }
            }
            vis1 = new boolean[n][m];
            vis2 = new boolean[n][m];
            vis1[coins.get(0).x][coins.get(0).y] = true;
            vis2[coins.get(1).x][coins.get(1).y] = true;
            ans = Integer.MAX_VALUE;
            set = new HashSet<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int x1 = coins.get(0).x;
        int y1 = coins.get(0).y;
        int x2 = coins.get(1).x;
        int y2 = coins.get(1).y;
        dfs(x1, y1, x2, y2, 0);
        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    public void dfs(int x1, int y1, int x2, int y2, int k) {
        if(k > 10) return ;

        if(isOut(x1, y1) && isOut(x2, y2)) {
            return ;
        }

        if((isOut(x1, y1) && !isOut(x2, y2)) || (!isOut(x1, y1) && isOut(x2, y2))) {
            ans = Math.min(ans, k);
            return ;
        }

        for(int dir=0; dir<4; dir++) {
            int nx1 = x1 + DX[dir];
            int ny1 = y1 + DY[dir];

            int nx2 = x2 + DX[dir];
            int ny2 = y2 + DY[dir];

            if(!isOut(nx1, ny1) && board[nx1][ny1] == '#') {
                nx1 = x1;
                ny1 = y1;
            }

            if(!isOut(nx2, ny2) && board[nx2][ny2] == '#') {
                nx2 = x2;
                ny2 = y2;
            }

            dfs(nx1, ny1, nx2, ny2, k+1);

        }
    }


    public boolean isOut(int x, int y) {
        return !(0<=x && 0<=y && x<n && y<m);
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