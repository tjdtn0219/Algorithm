import java.util.*;
import java.io.*;

public class Main {

    public static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][10];
        for(int i=1; i<=9; i++) {
            dp[1][i]++;
        }

        for(int i=2; i<=n; i++) {
            for(int j=0; j<=9; j++) {
                if (j==0) {
                    if(i==2) continue;
                    dp[i][j+1] += dp[i-1][j]%MOD;
                    dp[i][j+1] %= MOD;
                }
                else if(j==9) {
                    dp[i][j-1] += dp[i-1][j]%MOD;
                    dp[i][j-1] %= MOD;

                } else {
                    dp[i][j+1] += dp[i-1][j]%MOD;
                    dp[i][j+1] %= MOD;
                    dp[i][j-1] += dp[i-1][j]%MOD;
                    dp[i][j-1] %= MOD;
                }
            }
        }

        int ans = 0;
        for(int i=0; i<=9; i++) {
//            System.out.println(dp[n][i]);
            ans += dp[n][i];
            ans %= MOD;
        }

        System.out.println(ans);

    }
}
