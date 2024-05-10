import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 50001;

    int n, m;
    int[] guests;
    int[] sum;
    int[][] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        initSum();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            guests = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = Integer.parseInt(br.readLine());
            dp = new int[4][MAX];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void initSum() {
        sum = new int[MAX];
        sum[1] = guests[0];
        for(int i=2; i<=n; i++) {
            sum[i] = sum[i-1] + guests[i-1];
        }
    }

    public void solve() {
        for(int i=1; i<4; i++) {
            for(int j = i*m; j<=n; j++) {
                /* dp[i][j] : i번째 소형기관차가 j번째 객차까지 태운 최대 승객 합 */
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-m] + (sum[j] - sum[j-m]));
            }
        }
        System.out.println(dp[3][n]);
    }
}