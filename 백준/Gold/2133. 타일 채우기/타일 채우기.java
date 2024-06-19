import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] dp;

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
            dp = new int[n+5];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
       //dp[n] = dp[n-2] % 3 + dp[n-4]*2 + ...dp[0] * 2
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;
        for(int i=4; i<=n; i+=2) {
            dp[i] = dp[i-2] * 3;
            for(int j=i-4; j>=0; j-=2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[n]);
    }

}