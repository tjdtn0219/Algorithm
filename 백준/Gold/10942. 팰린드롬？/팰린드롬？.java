import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    int[] arr;
    int[][] seArr;
    boolean[][] dp; //dp[i][j] : i~j까지 palindrome인지 아닌지

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void solve() {
        initDp();
        StringBuilder sb = new StringBuilder();
        for(int[] se : seArr) {
            int s = se[0];
            int e = se[1];
            if(dp[s][e]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.println(sb);
    }

    public void initDp() {
        for(int i=1; i<=n; i++) {
            dp[i][i] = true;
        }

        for(int i=1; i<=n-1; i++) {
            if(arr[i] == arr[i+1]) {
                dp[i][i+1] = true;
            }
        }

        for(int j=2; j<=n-1; j++) {
            for(int i=1; i<=n-j; i++) {
                if(arr[i] == arr[i+j] && dp[i+1][i+j-1]) {
                    dp[i][i+j] = true;
                }
            }
        }
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            String[] tmp = br.readLine().split(" ");
            for(int i=1; i<=n; i++) {
                arr[i] = Integer.parseInt(tmp[i-1]);
            }
            m = Integer.parseInt(br.readLine());
            seArr = new int[m][2];
            for(int i=0; i<m; i++) {
                seArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            dp = new boolean[n+1][n+1];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

}