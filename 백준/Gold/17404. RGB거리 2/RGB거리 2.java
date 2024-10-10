import java.io.*;

public class Main {

    static final int INF = 1000*1000;

    int n;
    int[][] costs;
    int[][] dp;

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
            costs = new int[n+1][3];
            dp = new int[n+1][3];
            for(int i=0; i<n; i++) {
                String[] r_g_b = br.readLine().split(" ");
                int r = Integer.parseInt(r_g_b[0]);
                int g = Integer.parseInt(r_g_b[1]);
                int b = Integer.parseInt(r_g_b[2]);
                costs[i+1][0] = r;
                costs[i+1][1] = g;
                costs[i+1][2] = b;
            }
            
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {

        int ans = INF;
        for(int k=0; k<3; k++) {
            for(int i=0; i<3; i++) {
                if(i == k) dp[1][i] = costs[1][i];
                else dp[1][i] = INF;
            }

            for(int i=2; i<=n; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
            }

            for(int i=0; i<3; i++) {
                if(i != k) ans = Math.min(ans, dp[n][i]);
            }
        }

        System.out.println(ans);


    }

}