import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 10_001;

    int n, k;
    int[] coins;
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
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            k = Integer.parseInt(tmp[1]);
            coins = new int[n];
            for(int i=0; i<n; i++) {
                coins[i] = Integer.parseInt(br.readLine());
            }
            dp = new int[MAX];
            Arrays.fill(dp, MAX);
            dp[0] = 0;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int coin : coins) {
            for(int i=coin; i<=k; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
        if(dp[k] == MAX) System.out.println(-1);
        else System.out.println(dp[k]);
    }



}
