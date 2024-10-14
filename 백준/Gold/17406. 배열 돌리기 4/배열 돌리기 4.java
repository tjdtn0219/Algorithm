import java.io.*;

public class Main {
    
    int n, m, k;
    int[][] originBoard;
    int[][] board;
    int[][] commands;
    int[] comb;
    boolean[] vis;
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
            String[] n_m_k = br.readLine().split(" ");
            n = Integer.parseInt(n_m_k[0]);
            m = Integer.parseInt(n_m_k[1]);
            k = Integer.parseInt(n_m_k[2]);
            board = new int[n][m];
            originBoard = new int[n][m];
            commands = new int[k][3];
            for(int i=0; i<n; i++) {
                String[] s = br.readLine().split(" ");
                for(int j=0; j<m; j++) {
                    board[i][j] = Integer.parseInt(s[j]);
                    originBoard[i][j] = board[i][j];
                }
            }
            for(int i=0; i<k; i++) {
                String[] r_c_s = br.readLine().split(" ");
                commands[i][0] = Integer.parseInt(r_c_s[0]);
                commands[i][1] = Integer.parseInt(r_c_s[1]);
                commands[i][2] = Integer.parseInt(r_c_s[2]);
            }
            comb = new int[k];
            vis = new boolean[k];
            ans = Integer.MAX_VALUE;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        makeComb(0);
        System.out.println(ans);
    }

    private void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void makeComb(int dep) {
        if(k == dep) {
            // printComb();
            doRoate();
            ans = Math.min(ans, getMinVal());
            for(int i=0; i<n; i++) {
                board[i] = originBoard[i].clone();
            }
            return ;
        }

        for(int i=0; i<k; i++) {
            if(vis[i]) continue;
            comb[dep] = i;
            vis[i] = true;
            makeComb(dep+1);
            vis[i] = false;
        }
    }

    public void doRoate() {
        for(int i=0; i<k; i++) {
            int idx = comb[i];
            int[] command = commands[idx];
            rotateMatrix(command);
        }
    }

    public int getMinVal() {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=0; j<m; j++) {
                sum += board[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    public void rotateMatrix(int[] command) {
        int r = command[0];
        int c = command[1];
        int s = command[2];

        for(int i=1; i<=s; i++) {
            rotateOutLine(r-1, c-1, i);
        }
    }

    public void rotateOutLine(int r, int c, int s) {
        int x = r - s;
        int y = c + s;
        
        int tmp1 = board[x][y];
        for(int i=0; i<2*s; i++) {
            // right
            board[x][y] = board[x][y-1];
            y--;
        }

        x = r + s;
        y = c + s;
        int tmp2 = board[x][y];
        for(int i=0; i<2*s; i++) {
            // down
            if(i==2*s-1) {
                board[x][y] = tmp1;
            } else {
                board[x][y] = board[x-1][y];
                x--;
            }
        }

        x = r + s;
        y = c - s;
        tmp1 = board[x][y];
        for(int i=0; i<2*s; i++) {
            // left
            if(i==2*s-1) {
                board[x][y] = tmp2;
            } else {
                board[x][y] = board[x][y+1];
                y++;
            }
        }

        x = r - s;
        y = c - s;
        for(int i=0; i<2*s; i++) {
            // up
            if(i==2*s-1) {
                board[x][y] = tmp1;
            } else {
                board[x][y] = board[x+1][y];
                x++;
            }
        }

    }

    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}