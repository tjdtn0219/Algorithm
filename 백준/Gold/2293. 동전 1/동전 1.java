import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 10_001;

    int n, k;
    int[] arr;
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
            arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);
            dp = new int[MAX];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void initDP() {
        for(int i=0; i<n; i++) {

        }
    }

    public void solve() {
        //dp[i] : 합이 i원일 때 경우의 수
        dp[0] = 1;
        for(int num : arr) {
            for(int i=num; i<=k; i++) {
                dp[i] = dp[i] + dp[i-num];
            }
        }
        System.out.println(dp[k]);

    }
}