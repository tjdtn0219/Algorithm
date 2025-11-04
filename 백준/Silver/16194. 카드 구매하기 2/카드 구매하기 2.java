import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            dp[i] = arr[i];
            for(int j=1; j<=i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j] + arr[j]);
            }
        }

//        StringBuilder sb = new StringBuilder();
//        for(int num : dp) {
//            sb.append(num).append(" ");
//        }
//        System.out.println(sb);

        System.out.println(dp[n]);
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            String[] tmp = br.readLine().split(" ");
            arr = new int[n+1];
            for(int i=1; i<=n; i++) {
                arr[i] = Integer.parseInt(tmp[i-1]);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}