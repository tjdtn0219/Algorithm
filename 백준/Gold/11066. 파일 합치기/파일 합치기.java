import java.io.*;
import java.util.*;

public class Main {

    int T;
    int n;
    int[] files;
    int[] sums;
    int[][] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
//        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++) {
                n = Integer.parseInt(br.readLine());
                files = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                sums = new int[n];
                sums[0] = files[0];
                for(int i=1; i<n; i++) {
                    sums[i] = sums[i-1] + files[i];
                }
                dp = new int[n][n];
                solve();
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        //dp[i][j] = dp[i][m]+dp[m+1][j]+(i~m까지의 파일 합)+(m+1~j까지 파일 합)
        for(int k=1; k<n; k++) {
            for(int i=0; i<n-k; i++) {
                int j = i + k;
                dp[i][j] = Integer.MAX_VALUE;

                for(int mid=i; mid<j; mid++) {
                    if(i==0) dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid+1][j] + sums[j]);
                    else dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid+1][j] + sums[j] - sums[i-1]);
                }
            }
        }

        System.out.println(dp[0][n-1]);
    }
}