import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_LEN = 10_000;
    int n, m;
    int[] memories;
    int[] costs;
    int[][] dp;
    int answer;

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
            m = Integer.parseInt(tmp[1]);
            tmp = br.readLine().split(" ");
            memories = new int[n];
            costs = new int[n];
            for(int i=0; i<n; i++) {
                memories[i] = Integer.parseInt(tmp[i]);
            }
            tmp = br.readLine().split(" ");
            for(int i=0; i<n; i++) {
                costs[i] = Integer.parseInt(tmp[i]);
            }
            dp = new int[n][MAX_LEN + 5];
            answer = Integer.MAX_VALUE;
        } catch (Exception e) {
            System.out.println("InPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void solve() {
        //dp[i][j] : i번째 까지 앱을 사용할 때, 비용 j으로 확보 가능한 최대 메모리 크기
        for(int i=0; i<n; i++) {
            int memory = memories[i];
            int cost = costs[i];

            for(int j=0; j<=MAX_LEN; j++) {
                if(i == 0) {
                    if(j >= cost) {
                        dp[i][j] = memory;
                    }
                } else {
                    if(j >= cost) dp[i][j] = Math.max(dp[i-1][j-cost] + memory, dp[i-1][j]);
                    else dp[i][j] = dp[i-1][j];
                }

                if(dp[i][j] >= m) answer = Math.min(answer, j);
            }
        }
        System.out.println(answer);
    }
}