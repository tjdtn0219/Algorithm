import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    int[][] board;
    int[][] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void solve() {
        for(int i=0; i<n; i++) {
            dp[i][0] = board[i][0];
        }

        for(int j=0; j<m; j++) {
            dp[0][j] = board[0][j];
        }

        for(int i=1; i<n; i++) {
            for(int j=1; j<m; j++) {
                if(board[i][j] == 1) {
                    int min = Math.min(dp[i-1][j], dp[i][j-1]);
                    if(board[i-min][j-min] == 0) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = min + 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println(findMaxVal());

    }

    public int findMaxVal() {
        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                // System.out.print(dp[i][j] + " ");
                max = Math.max(max, dp[i][j]);
            }
            // System.out.println();
        }
        return max*max;
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_m = br.readLine().split(" ");
            n = Integer.parseInt(n_m[0]);
            m = Integer.parseInt(n_m[1]);
            board = new int[n][m];
            dp = new int[n][m];
            
            for(int i=0; i<n; i++) {
                String s = br.readLine();
                for(int j=0; j<m; j++) {
                    board[i][j] = s.charAt(j) - '0';
                }
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

}