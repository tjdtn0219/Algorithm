import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Cleaner {
        Point p1, p2;
        public Cleaner(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,-1,0,1};

    static int n, m;
    static int T;
    static int[][] board;
    static Cleaner cleaner;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        for(int t=0; t<T; t++) {
            spread();
//            printBoard();
            clean();
//            break;
//            printBoard();
        }

        int ans = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] > 0) {
                    ans += board[i][j];
                }
            }
        }
        System.out.println(ans);
    }

    public static void clean() {
        rotateReverse(cleaner.p1);
        rotate(cleaner.p2);
    }

    public static void rotateReverse(Point p) {
        int tmp = board[p.x][m-1];
        // -->
        for(int j=m-2; j>=0; j--) {
            board[p.x][j+1] = board[p.x][j];
        }
        // 아래
        for(int i=p.x-1; i>=0; i--) {
            board[i+1][0] = board[i][0];
        }
        // <--
        for(int j=1; j<m; j++) {
            board[0][j-1] = board[0][j];
        }
        // 위
        for(int i=1; i<p.x; i++) {
            board[i-1][m-1] = board[i][m-1];
        }
        board[p.x-1][m-1] = tmp;
        board[p.x][p.y] = 0;
    }

    public static void rotate(Point p) {
        int tmp = board[p.x][m-1];
        // -->
        for(int j=m-2; j>=0; j--) {
            board[p.x][j+1] = board[p.x][j];
        }
        // 위
        for(int i=p.x+1; i<n; i++) {
            board[i-1][0] = board[i][0];
        }
        // <--
        for(int j=1; j<m; j++) {
            board[n-1][j-1] = board[n-1][j];
        }
        // 아래
        for(int i=n-2; i>p.x; i--) {
            board[i+1][m-1] = board[i][m-1];
        }
        board[p.x+1][m-1] = tmp;
        board[p.x][p.y] = 0;
    }

    public static void spread() {
        int[][] tmp = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] > 0) {
                    int sum = 0;
                    int num = board[i][j] / 5;
                    for(int dir=0; dir<4; dir++) {
                        int nx = i + DX[dir];
                        int ny = j + DY[dir];
                        if(!isInner(nx, ny)) continue;
                        if(isCleaner(nx, ny)) continue;
                        tmp[nx][ny] += num;
                        sum += num;
                    }
                    tmp[i][j] += (board[i][j] - sum);
                }
            }
        }
        board = tmp;
    }

    public static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }

    public static boolean isCleaner(int x, int y) {
        Point p = new Point(x, y);
        if(cleaner.p1.equals(p) || cleaner.p2.equals(p)) return true;
        return false;
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_m_t = br.readLine().split(" ");
            n = Integer.parseInt(n_m_t[0]);
            m = Integer.parseInt(n_m_t[1]);
            T = Integer.parseInt(n_m_t[2]);
            board = new int[n][m];
            Point p1 = null;
            Point p2 = null;
            boolean flag = false;
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                for(int j=0; j<m; j++) {
                    board[i][j] = Integer.parseInt(tmp[j]);
                    if(board[i][j] == -1) {
                        if(!flag) {
                            p1 = new Point(i, j);
                            flag = true;
                        } else {
                            p2 = new Point(i, j);
                        }
                        board[i][j] = 0;
                    }
                }
            }
            cleaner = new Cleaner(p1, p2);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}
