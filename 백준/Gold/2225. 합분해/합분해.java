import java.io.*;
import java.util.*;

public class Main {

    static final long MOD = 1_000_000_000;

    int n, k;
    int answer;
    long[][] dp;
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
            k = Integer.parseInt(tmp[1]);
            dp = new long[k+1][n+1];
            answer = 0;
        } catch (Exception e) {
            System.out.println("InPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void solve() {
        //DP[K][N] = DP[K-1][0] + DP[K-1][1] + … + DP[K-1][N]
        //DP[K][N] = DP[K-1][N] + DP[K][N-1]

        for(int i=0; i<=n; i++) {
            dp[1][i] = 1;
            dp[0][i] = 0;
        }

        for(int i=0; i<=k; i++) {
            dp[i][1] = i;
        }

        for(int i=2; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                dp[j][i] = (dp[j-1][i] + dp[j][i-1]) % MOD;
            }
        }
        System.out.println(dp[k][n] % MOD);
    }

}