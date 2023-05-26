import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());

        long[][] dp = new long[n+1][2];
        //1, 10, 100, 101, 1000, 1001
        //dp[i][0] => i번째 0이 올 경우, dp[i][0] = dp[i-1][0] + dp[i-1][1]
        //dp[i][1] => i번째 1이 올 경우, dp[i][1] = dp[i-1][0]

        dp[1][0] = 0;
        dp[1][1] = 1;
        for(int i=2; i<=n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        long ans = dp[n][0] + dp[n][1];

        System.out.println(ans);
    }
}